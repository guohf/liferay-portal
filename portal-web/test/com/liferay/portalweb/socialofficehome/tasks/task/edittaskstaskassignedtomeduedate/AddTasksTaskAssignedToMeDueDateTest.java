/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.socialofficehome.tasks.task.edittaskstaskassignedtomeduedate;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AddTasksTaskAssignedToMeDueDateTest extends BaseTestCase {
	public void testAddTasksTaskAssignedToMeDueDate() throws Exception {
		int label = 1;

		while (label >= 1) {
			switch (label) {
			case 1:
				selenium.open("/user/joebloggs/home/");

				for (int second = 0;; second++) {
					if (second >= 90) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible("//nav/ul/li[1]/a/span")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				assertEquals(RuntimeVariables.replace("Home"),
					selenium.getText("//nav/ul/li[1]/a/span"));
				selenium.clickAt("//div[2]/div[1]/ul/li[5]/a",
					RuntimeVariables.replace("Tasks"));
				selenium.waitForPageToLoad("30000");
				assertEquals(RuntimeVariables.replace("Tasks"),
					selenium.getText("//h1/span[2]"));
				assertEquals(RuntimeVariables.replace("No tasks were found."),
					selenium.getText("//div[@class='portlet-msg-info']"));
				assertEquals(RuntimeVariables.replace("Add Task"),
					selenium.getText("link=Add Task"));
				selenium.clickAt("link=Add Task",
					RuntimeVariables.replace("Add Task"));

				for (int second = 0;; second++) {
					if (second >= 90) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible("//h1/span")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				assertEquals(RuntimeVariables.replace("Add Task"),
					selenium.getText("//h1/span"));

				for (int second = 0;; second++) {
					if (second >= 90) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible("//td[2]/input")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				selenium.type("//td[2]/input",
					RuntimeVariables.replace("Task Description"));
				selenium.select("//select",
					RuntimeVariables.replace("label=Joe Bloggs"));
				selenium.select("//tr[4]/td[2]/select",
					RuntimeVariables.replace("label=Normal"));

				for (int second = 0;; second++) {
					if (second >= 90) {
						fail("timeout");
					}

					try {
						if (selenium.isVisible(
									"_1_WAR_tasksportlet_neverDueCheckbox")) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				assertTrue(selenium.isElementPresent(
						"_1_WAR_tasksportlet_neverDueCheckbox"));

				boolean neverDueChecked = selenium.isChecked(
						"_1_WAR_tasksportlet_neverDueCheckbox");

				if (!neverDueChecked) {
					label = 2;

					continue;
				}

				selenium.clickAt("_1_WAR_tasksportlet_neverDueCheckbox",
					RuntimeVariables.replace("Uncheck Never Due"));

			case 2:
				selenium.select("//select[@id='_1_WAR_tasksportlet_dueDateMonth']",
					RuntimeVariables.replace("label=March"));
				selenium.select("//select[@id='_1_WAR_tasksportlet_dueDateDay']",
					RuntimeVariables.replace("label=16"));
				selenium.select("//select[@id='_1_WAR_tasksportlet_dueDateYear']",
					RuntimeVariables.replace("label=2016"));
				selenium.clickAt("//input[@value='Save']",
					RuntimeVariables.replace("Save"));

				for (int second = 0;; second++) {
					if (second >= 90) {
						fail("timeout");
					}

					try {
						if (RuntimeVariables.replace("Task Description")
												.equals(selenium.getText(
										"//h1/span"))) {
							break;
						}
					}
					catch (Exception e) {
					}

					Thread.sleep(1000);
				}

				assertEquals(RuntimeVariables.replace("Task Description"),
					selenium.getText("//h1/span"));
				assertEquals(RuntimeVariables.replace("Assigned to Joe Bloggs"),
					selenium.getText("//div[2]/div[2]/div[1]"));

			case 100:
				label = -1;
			}
		}
	}
}