package com.videojs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
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

        panel.add(videoUrl);

        Button showBtn = new Button("Show video");

        showBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (player == null) {
                    player = new VideoPlayer(854, 480);

                    player.addSource(videoUrl.getValue(), VideoElement.TYPE_MP4);

                    panel.add(player);
                }
            }
        });

        panel.add(showBtn);

        RootPanel.get("container").add(panel);
    }
}
