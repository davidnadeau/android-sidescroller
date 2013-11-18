package com.example.sidescroller.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by soote on 11/18/13.
 */
public class SurfaceView extends GLSurfaceView {

    private final mRenderer r;

    public SurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the mRenderer for drawing on the GLSurfaceView
        r = new mRenderer();
        setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        setRenderer(r);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        return true;
    }
}
