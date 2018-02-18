package org.bs.serveaem.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

/**
 * Getting MultiField Dialog Properties
 *
 */

@Model(adaptables = Resource.class)
public class ImageGallery {

	@Inject
	@Optional
	public Resource galleryItems;

}