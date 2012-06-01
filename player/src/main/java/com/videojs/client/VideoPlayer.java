package com.videojs.client;

import static com.videojs.client.VideoPlayerResources.RESOURCES;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.SourceElement;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for the video player. Can be used as ordinary widget.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 * @version $Id$
 */
public class VideoPlayer extends Widget {
    private static final String FALLBACK_SWF = GWT.getModuleBaseURL() + "/videojs/video-js-3.2.0.swf";

    private String playerId;

    public VideoPlayer(int width, int height, String source) {
        RESOURCES.css().ensureInjected();

        setFlashFallback(FALLBACK_SWF);

        final Document doc = Document.get();

        DivElement container = doc.createDivElement();

        setElement(container);

        VideoElement videoElem = doc.createVideoElement();

        videoElem.setId(playerId = doc.createUniqueId());
        videoElem.addClassName("video-js");
        videoElem.addClassName("vjs-default-skin");
        videoElem.setWidth(width);
        videoElem.setHeight(height);
        videoElem.setControls(true);
        videoElem.setPreload(MediaElement.PRELOAD_NONE);

        videoElem.setPoster("http://video-js.zencoder.com/oceans-clip.png");

        SourceElement srcElem = doc.createSourceElement();

        srcElem.setSrc(source);
        srcElem.setType("video/mp4");

        videoElem.appendChild(srcElem);

        container.appendChild(videoElem);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        initPlayer(playerId);
    }

    private native void setFlashFallback(String swfUrl) /*-{
        $wnd._V_.options.flash.swf = swfUrl;
    }-*/;

    private native void initPlayer(String videoId) /*-{
        $wnd._V_(videoId, {}, function() {
            $wnd.window.alert("Player initialized");
        });
    }-*/;
}
