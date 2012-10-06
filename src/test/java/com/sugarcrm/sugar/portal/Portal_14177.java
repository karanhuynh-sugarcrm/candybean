package com.sugarcrm.sugar.portal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sugarcrm.sugar.Sugar;
import com.sugarcrm.sugar.SugarTest;
import com.sugarcrm.sugar.admin.Administration;
import com.sugarcrm.sugar.users.User.PortalUser;
import com.sugarcrm.sugar.users.User.UserBuilder;
import com.sugarcrm.sugar.users.Users;


public class Portal_14177 extends SugarTest {
	
	private PortalUser portalUser;
	
	@BeforeClass
	public static void setupOnce() throws Exception { SugarTest.setupOnce(); }

	@Override
	@Before
	public void setup() throws Exception {
		super.setup();
//		1. Portal side is installed successfully.
		Administration.selectPortalEnable(voodoo, sugar);
//		2. Create one portal user in Sugar server side.
		UserBuilder ub = new UserBuilder("cw_test", "Conrad Warmbold", "password1", "password1");
		portalUser = ub.build().buildPortalUser();
		Users.createPortalUser(voodoo, sugar, portalUser);
//		3. Create a contact record with portal name, portal access URL, password and account fields filled, such as:
//		Contact Name -> contact1, Account Name -> account1, Portal Name -> PortalTest1, Password-> a, check box "Portal Active"
	}
	
	@Test
	public void test() throws Exception {
//		1. Login to Portal side as a valid user mentioned in precondition2.
		Sugar.logout(voodoo, sugar);
		Sugar.login(voodoo, sugar, portalUser.username(), portalUser.password1());
//		2. Go to Cases module.
//		3. Click "Create New" link.
//		4. Fill all mandatory fields and click "Save" button.
//		5. Login to Sugar server side as a valid user, such as admin user.
		Sugar.logout(voodoo, sugar);
		Sugar.login(voodoo, sugar, "admin", "asdf");
//		6. Go to Cases module in server side. 
//		7. Search the case created from above.
//			7. ***The new case is found.
//		8. Edit the case record, such as all fields.
//			8. ***The case is updated successfully in Sugar server side.
//		9. Click Save button.
//		10. The same Portal user log in to Portal again.
		Sugar.logout(voodoo, sugar);
		Sugar.login(voodoo, sugar, portalUser.username(), portalUser.password1());
//			10.***The changes are also shown on the portal side.
//		11. Navigate to Cases tab and search the same case.	
		Sugar.logout(voodoo, sugar);
		Sugar.login(voodoo, sugar, "admin", "asdf");
	}

	@Override
	@After
	public void cleanup() throws Exception {
		Administration.selectPortalEnable(voodoo, sugar);
		super.cleanup();
	}

	@AfterClass
	public static void cleanupOnce() { SugarTest.cleanupOnce(); }
}
