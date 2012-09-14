package com.videojs.client;

import static com.google.gwt.core.client.GWT.getModuleBaseURL;
import static com.videojs.client.VideoPlayerResources.RESOURCES;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.SourceElement;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for the video player. Can be used as ordinary widget.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 * @version $Id$
 */
public class VideoPlayer extends Widget {
    static final String VERSION = "3.2.0";

    private static final String FALLBACK_SWF = getModuleBaseURL() + "/videojs/video-js-" + VERSION + ".swf";
    private static final String DEFAULT_PRELOAD = MediaElement.PRELOAD_NONE;

    private final int width;
    private final int height;

    private String skinName = "vjs-default-skin";
    private boolean controls = true;
    private String preload = DEFAULT_PRELOAD;
    private String poster = null;

    private List<String> sources = new ArrayList<String>();
    private List<String> sourceType = new ArrayList<String>();

    private JavaScriptObject playerObject;

    public VideoPlayer(int width, int height) {
        if (RESOURCES.css().ensureInjected()) {
            setFlashFallback();
        }

        this.width = width;
        this.height = height;

        setElement(Document.get().createDivElement());
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        final String playerId = Document.get().createUniqueId();

        VideoElement videoElem = Document.get().createVideoElement();

        videoElem.setId(playerId);
        videoElem.addClassName("video-js");
        videoElem.setWidth(width);
        videoElem.setHeight(height);

        if (skinName != null) {
            videoElem.addClassName(skinName);
        }

        videoElem.setControls(controls);

        if (preload != null) {
            videoElem.setPreload(preload);
        } else {
            videoElem.setPreload(DEFAULT_PRELOAD);
        }

        if (poster != null) {
            videoElem.setPoster(poster);
        }

        if ((sources.size() == 0) || (sources.size() != sourceType.size())) {
            throw new IllegalArgumentException("Wrong number of video sources");
        }

        for (int i = 0; i < sources.size(); i++) {
            SourceElement srcElem = Document.get().createSourceElement();

            srcElem.setSrc(sources.get(i));
            srcElem.setType(sourceType.get(i));

            videoElem.appendChild(srcElem);
        }

        getElement().appendChild(videoElem);

        this.playerObject = initPlayer(playerId);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.Widget#onUnload()
     */
    @Override
    protected void onUnload() {
        super.onUnload();

        this.playerObject = null;
    }

    /**
     * Start the video playback.
     */
    public native void play()  /*-{
        var player = this.@com.videojs.client.VideoPlayer::playerObject;

        if (player) {
            player.play();
        }
    }-*/;

    /**
     * Pause the video playback.
     */
    public native void pause() /*-{
        var player = this.@com.videojs.client.VideoPlayer::playerObject;

        if (player) {
            player.pause();
        }
    }-*/;

    /**
     * Set skin name.
     *
     * @param skinName the skinName to set
     */
    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    /**
     * Show controls for the player.
     *
     * @param controls the controls to set
     */
    public void setControls(boolean controls) {
        this.controls = controls;
    }

    /**
     * Set preload type for the player. MediaElement.PRELOAD_NONE by default
     * @param preload the preload to set
     */
    public void setPreload(String preload) {
        this.preload = preload;
    }

    /**
     * Add source for video tag. Type value could be from class VideoElement
     * @param src
     * @param type
     */
    public void addSource(String src, String type) {
        sources.add(src);
        sourceType.add(type);
    }

    /**
     * @param poster the poster to set
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    private native void setFlashFallback() /*-{
        $wnd._V_.options.flash.swf = @com.videojs.client.VideoPlayer::FALLBACK_SWF;
    }-*/;

    private native JavaScriptObject initPlayer(String videoId) /*-{
        return $wnd._V_(videoId, {}, function() {});
    }-*/;

    private native void play(JavaScriptObject player) /*-{
        player.play();
    }-*/;
}
