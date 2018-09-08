/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.zooplus.test.setup;

import static com.zooplus.test.constants.ZooplustestConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.zooplus.test.constants.ZooplustestConstants;
import com.zooplus.test.service.ZooplustestService;


@SystemSetup(extension = ZooplustestConstants.EXTENSIONNAME)
public class ZooplustestSystemSetup
{
	private final ZooplustestService zooplustestService;

	public ZooplustestSystemSetup(final ZooplustestService zooplustestService)
	{
		this.zooplustestService = zooplustestService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		zooplustestService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ZooplustestSystemSetup.class.getResourceAsStream("/zooplustest/sap-hybris-platform.png");
	}
}
