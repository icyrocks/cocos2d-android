package org.cocos2d.transitions;

import org.cocos2d.actions.ease.EaseOut;
import org.cocos2d.actions.instant.CallFunc;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.ScaleTo;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.types.CCSize;

/**
 * Shrink the outgoing scene while grow the incoming scene
 */
public class ShrinkGrowTransition extends TransitionScene {
    public ShrinkGrowTransition(float t, Scene s) {
        super(t, s);
    }

    public void onEnter() {
        super.onEnter();

        CCSize s = Director.sharedDirector().winSize();

        inScene.scale(0.001f);
        outScene.scale(1.0f);

        inScene.setAnchorPoint(2/3.0f,0.5f);
        outScene.setAnchorPoint(1/3.0f,0.5f);	


        IntervalAction scaleOut = ScaleTo.action(duration, 0.01f);
        IntervalAction scaleIn = ScaleTo.action(duration, 1.0f);

        inScene.runAction(EaseOut.action(scaleIn, 2.0f));
        outScene.runAction(Sequence.actions(
                EaseOut.action(scaleOut, 2.0f),
                CallFunc.action(this, "finish")));
    }

}
