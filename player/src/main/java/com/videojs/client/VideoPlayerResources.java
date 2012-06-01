package com.videojs.client;

import static com.google.gwt.core.client.GWT.create;
import static com.videojs.client.VideoPlayer.VERSION;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;

/**
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
interface VideoPlayerResources extends ClientBundle {
    VideoPlayerResources RESOURCES = create(VideoPlayerResources.class);

    @Source("video-js-" + VERSION + ".css")
    CssResource css();

    @Source("video-js-" + VERSION + ".png")
    public DataResource buttonsAndDefaultSkin();
}
