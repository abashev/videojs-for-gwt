package com.videojs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
public class SandboxEntryPoint implements EntryPoint {
    private VideoPlayer player;

    /* (non-Javadoc)
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        final VerticalPanel panel = new VerticalPanel();

        Button hideBtn = new Button("Remove from page");

        hideBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (player != null) {
                    player.removeFromParent();

                    player = null;
                }
            }
        });

        panel.add(hideBtn);

        final TextBox videoUrl = new TextBox();

        videoUrl.setValue("http://video-js.zencoder.com/oceans-clip.mp4");

        panel.add(videoUrl);

        Button showBtn = new Button("Show video");

        showBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (player == null) {
                    player = new VideoPlayer(854, 480);

                    player.addSource(videoUrl.getValue(), VideoElement.TYPE_MP4);

                    panel.add(player);

                    panel.add(new Button("Play", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            player.play();
                        }
                    }));

                    panel.add(new Button("Pause", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            player.pause();
                        }
                    }));

                    final Label currentPosition = new Label("Current position: ");

                    player.addTimeUpdateHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            currentPosition.setText("Current position: " + player.getCurrentTime());
                        }
                    });

                    panel.add(currentPosition);


                    player.addLoadedMetadataHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            player.rewind(15);
                        }
                    });

                    player.addLoadedDataHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            player.rewind(15);
                        }
                    });

                    final Label currentStatus = new Label("No status");

                    panel.add(currentStatus);

                    player.addPlayHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            currentStatus.setText("Player start playing");
                        }
                    });

                    player.addPauseHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            currentStatus.setText("Player paused");
                        }
                    });

                    player.addEndedHandler(new VideoPlayerHandler() {
                        public void handle(VideoPlayer player) {
                            currentStatus.setText("Player ended");
                        }
                    });

                    final TextBox position = new TextBox();

                    Button setPositionBtn = new Button("Jump", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            player.setCurrentTime(Integer.valueOf(position.getValue()));
                            //                            player.rewind(Integer.valueOf(position.getValue()));
                        }
                    });

                    panel.add(position);
                    panel.add(setPositionBtn);
                }
            }
        });

        panel.add(showBtn);

        RootPanel.get("container").add(panel);
    }
}
