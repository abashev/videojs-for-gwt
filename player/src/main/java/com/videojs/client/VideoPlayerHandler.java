package com.videojs.client;

/**
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
public interface VideoPlayerHandler {
    /**
     * Handle player event.
     *
     * @param player - source player.
     */
    void handle(VideoPlayer player);
}
