package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddUserDriver;
import com.zixi.drivers.drivers.ZenAddUserGroupDriver;
import com.zixi.drivers.drivers.ZenAddUserRoleDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddUserRoleTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddUserRoleDriver(); }

	@Parameters({"zen_userName", "zen_userPass" ,"login_ip", "uiport",
	"name", "clusters_edit", "clusters_view", "clusters_notify", "source_edit",
	"source_view", "source_notify", "adaptive_edit", "adaptive_view",
	"adaptive_notify", "delivery_edit", "delivery_view", "delivery_notify", "resource_tag_name", "testid"})
	@Test
	public void addUserGroupZen(String zen_userName, String zen_userPass, String login_ip, String uiport,
	String name, String clusters_edit, String clusters_view, String clusters_notify, String source_edit,
	String source_view, String source_notify, String adaptive_edit, String adaptive_view,
	String adaptive_notify, String delivery_edit, String delivery_view, String delivery_notify,
	String resource_tag_name, String testid) throws Exception { 
		
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zen_userName", "zen_userPass" ,"login_ip", "uiport",
		"name", "clusters_edit", "clusters_view", "clusters_notify", "source_edit",
		"source_view", "source_notify", "adaptive_edit", "adaptive_view",
		"adaptive_notify", "delivery_edit", "delivery_view", "delivery_notify", "resource_tag_name", "testid"}, 
		new String[] {zen_userName, zen_userPass ,login_ip, uiport,
		name, clusters_edit, clusters_view, clusters_notify, source_edit,
		source_view, source_notify, adaptive_edit, adaptive_view,
		adaptive_notify, delivery_edit, delivery_view, delivery_notify, resource_tag_name, testid});
		
		driverReslut = ((ZenAddUserRoleDriver) testDriver).addZenUserRole(zen_userName, zen_userPass ,login_ip, uiport,
		name, clusters_edit, clusters_view, clusters_notify, source_edit,
		source_view, source_notify, adaptive_edit, adaptive_view,
		adaptive_notify, delivery_edit, delivery_view, delivery_notify, resource_tag_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}