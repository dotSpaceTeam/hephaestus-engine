package team.unnamed.hephaestus.adapt.v1_16_R3;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import team.unnamed.hephaestus.model.Model;
import team.unnamed.hephaestus.model.animation.ModelAnimationQueue;
import team.unnamed.hephaestus.model.view.ModelView;
import team.unnamed.hephaestus.model.view.ModelViewAnimator;
import team.unnamed.hephaestus.model.view.ModelViewController;
import team.unnamed.hephaestus.model.view.ModelViewRenderer;

import java.util.Collection;

public class ModelViewRenderer_v1_16_R3 implements ModelViewRenderer {

    private final ModelViewController controller;
    private final ModelViewAnimator animator;

    public ModelViewRenderer_v1_16_R3(ModelViewAnimator animator) {
        this.animator = animator;
        this.controller = new ModelViewController_v1_16_R3();
    }

    @Override
    public ModelView render(
            Model model,
            Location location,
            ModelAnimationQueue animationQueue,
            Collection<Player> viewers
    ) {
        ModelView view = new ModelView(
                controller,
                animator,
                animationQueue,
                model,
                viewers,
                location
        );
        view.show();
        return view;
    }
}