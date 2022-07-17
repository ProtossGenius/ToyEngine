package com.suremoon.game.configers.map_resource.frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Water Moon on 2018/5/23.
 */
public class MSplitPane extends JSplitPane{


    public MSplitPane() {
        super();
    }

    public MSplitPane(int newOrientation) {
        super(newOrientation);
    }

    public MSplitPane(int newOrientation, boolean newContinuousLayout) {
        super(newOrientation, newContinuousLayout);
    }

    public MSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent) {
        super(newOrientation, newLeftComponent, newRightComponent);
    }

    public MSplitPane(int newOrientation, boolean newContinuousLayout, Component newLeftComponent, Component newRightComponent) {
        super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
    }
    //setDividerLocation

    @Override
    public void setDividerLocation(int location) {
        super.setDividerLocation(location);
        if (getOrientation() == HORIZONTAL_SPLIT) {//横着的
            leftComponent.setBounds(getX(), getY(), location, getHeight());
            rightComponent.setBounds(getX()+location+dividerSize, getY(), getWidth()-location-10, getHeight());
        } else {
            leftComponent.setBounds(getX(), getY(), getWidth(), location);
            rightComponent.setBounds(getX(), getY()+location+dividerSize, getWidth(), getHeight()-location-10);
        }
    }

}
