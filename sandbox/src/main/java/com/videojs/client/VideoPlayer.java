package com.videojs.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for the video player. Can be used as ordinary widget.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 * @version $Id$
 */
public class VideoPlayer extends Composite {
    private static VideoPlayerUiBinder uiBinder = GWT.create(VideoPlayerUiBinder.class);

    interface VideoPlayerUiBinder extends UiBinder<Widget, VideoPlayer> {
    }

    public VideoPlayer() {
        final Widget widget = uiBinder.createAndBindUi(this);

        initWidget(widget);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
//        setupAllWhenReady();
    }

    public native void setupAllWhenReady() /*-{
        $wnd._V_.options.flash.swf = "video-js.swf";
    }-*/;
}
