video.js for GWT
=====

The main objective of this project is to provide video.js integration for GWT applications. You can add video player on a page and manage it with standard GWT api (like UiBinder or so).

The project contains two parts - player itself and sandbox for doing functional tests.

How to use it
-----

First of all you need to add videojs-for-gwt into your project.

Repository - TBD

Maven dependency:

    <dependencies>
        <dependency>
            <groupId>com.github.videojs-for-gwt</groupId>
            <artifactId>player</artifactId>
            <version>3.2.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

Scope could be `provided` because it is client-only artifact.

Add `inherits` block into GWT module

    <inherits name="com.videojs.VideoPlayer" />

And now you ready to use it in your code.

    import com.videojs.client.VideoPlayer;
    import com.google.gwt.dom.client.VideoElement.TYPE_MP4;

    .........

    VideoPlayer player = new VideoPlayer(854, 480); // Create player with width/height

    player.addSource(someUrl, VideoElement.TYPE_MP4); // Add video mp4 source

    somePanel.add(player); // Add player into panel

And that is all.
