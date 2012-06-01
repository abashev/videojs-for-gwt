package com.videojs.client;

import static com.google.gwt.core.client.GWT.create;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;

/**
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
public interface VideoPlayerResources extends ClientBundle {
    VideoPlayerResources RESOURCES = create(VideoPlayerResources.class);

    @Source("video-js-3.2.0.css")
    CssResource css();

    @Source("video-js-3.2.0.png")
    public DataResource buttonsAndDefaultSkin();
}
