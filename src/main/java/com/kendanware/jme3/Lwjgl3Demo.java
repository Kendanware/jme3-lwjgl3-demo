package com.kendanware.jme3;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * @author Daniel Johansson
 * @since 2015-10-15
 */
public class Lwjgl3Demo extends SimpleApplication implements ActionListener {

    private AudioNode shootAudioNode;

    @Override
    public void simpleInitApp() {
        final AudioNode audioNode = new AudioNode(assetManager, "Sound/Environment/River.ogg");
        audioNode.setPositional(false);
        audioNode.setLooping(true);
        audioNode.play();

        shootAudioNode = new AudioNode(assetManager, "Sound/Effects/Gun.wav");
        shootAudioNode.setPositional(false);

        inputManager.addMapping("SHOOT", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(this, "SHOOT");
    }

    public static void main(final String... arguments) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        final AppSettings settings = new AppSettings(true);
        settings.setResolution(1920, 1080);

        final Lwjgl3Demo lwjgl3Demo = new Lwjgl3Demo();
        lwjgl3Demo.setSettings(settings);
        lwjgl3Demo.start(JmeContext.Type.Display);
    }

    @Override
    public void onAction(String name, boolean pressed, float value) {
        if ("SHOOT".equals(name) && pressed) {
            shootAudioNode.playInstance();
        }
    }
}
