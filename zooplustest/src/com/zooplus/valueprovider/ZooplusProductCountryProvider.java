/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.zooplus.valueprovider;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


/**
 *
 */
public class ZooplusProductCountryProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
private FieldNameProvider fieldNameProvider;

{

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof ProductModel)
		{
			final ProductModel product = (ProductModel) model;
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			fieldValues.addAll(createFieldValue(indexConfig, product, indexedProperty));
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot evaluate rating of non-product item");
		}
	}

	protected List<FieldValue> createFieldValue(final IndexConfig indexConfig, final ProductModel product,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final List<CountryModel> countryRows = new ArrayList<CountryModel>();
		final List<CountryModel> countries = product.getVisibleCountries();
		if (CollectionUtils.isNotEmpty(countries))
		{
			for (final CountryModel country : countries)
			{
				if (null != country.getIsocode())
				{
					countryRows.add(country);
				}
			}
		}
		if (CollectionUtils.isNotEmpty(countryRows))
		{
			addFieldValues(fieldValues, indexedProperty, countryRows);
		}
		return fieldValues;
	}
}
