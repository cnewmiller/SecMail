package edu.depaul.secmail;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainWindow {

	protected Shell shlSecmail;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
				
		shlSecmail.open();
		shlSecmail.layout();
		
		//get login info
		LoginDialog login = new LoginDialog(shlSecmail, SWT.PRIMARY_MODAL);
		LoginDialog.Status result = login.open();
		
		if (result != LoginDialog.Status.LOGIN_SUCCESS) // we exited or the login failed
			System.exit(0);
		
		while (!shlSecmail.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSecmail = new Shell();
		shlSecmail.setSize(466, 378);
		shlSecmail.setText("SecMail");
		shlSecmail.setLayout(new FormLayout());
		
		Composite composite = new Composite(shlSecmail, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(0, 298);
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 155);
		fd_composite.bottom = new FormAttachment(0, 299);
		composite.setLayoutData(fd_composite);
		
		Button btnNewEmail = new Button(composite, SWT.NONE);
		btnNewEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				NewMailWindow newMailWindow = new NewMailWindow();
				newMailWindow.open();
			}
		});
		GridData gd_btnNewEmail = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewEmail.widthHint = 133;
		btnNewEmail.setLayoutData(gd_btnNewEmail);
		btnNewEmail.setText("New Email");
		
		Button btnFetchMail = new Button(composite, SWT.NONE);
		GridData gd_btnFetchMail = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnFetchMail.widthHint = 131;
		btnFetchMail.setLayoutData(gd_btnFetchMail);
		btnFetchMail.setText("Fetch Mail");
		
		Button btnTestConnection = new Button(composite, SWT.NONE);
		btnTestConnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ConnectionTestWindow test = new ConnectionTestWindow(Display.getCurrent());
				test.open();
			}
		});
		GridData gd_btnTestConnection = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTestConnection.widthHint = 134;
		btnTestConnection.setLayoutData(gd_btnTestConnection);
		btnTestConnection.setText("Test Connection");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnClose = new Button(composite, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.exit(0);
			}
		});
		GridData gd_btnClose = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnClose.widthHint = 134;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");

	}
}
