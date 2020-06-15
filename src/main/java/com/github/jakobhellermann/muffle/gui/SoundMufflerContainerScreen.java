package com.github.jakobhellermann.muffle.gui;

import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import com.github.jakobhellermann.muffle.logic.SoundRegistry;
import spinnery.client.screen.BaseContainerScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

import java.util.List;

public class SoundMufflerContainerScreen extends BaseContainerScreen<SoundMufflerContainer> {
    AdvancedSoundMufflerBlockEntity blockEntity;

    String filter = null;

    WVerticalScrollableContainer createSoundsList(WPanel mainPanel) {
        WVerticalScrollableContainer soundsList = mainPanel.createChild(
                WVerticalScrollableContainer::new,
                Position.of(mainPanel, 18, 18, 0),
                Size.of(16 * 18, 6 * 18))
                .setDivisionSpace(9);

        List<String> allSounds = SoundRegistry.allSoundsInOrder(true);
        for (String sound : allSounds) {
            if (filter != null && !sound.contains(filter)) continue;

            WStaticText soundName = new WStaticText().setText(sound).setParent(soundsList);
            WToggle toggle = new WToggle().setToggleState(!this.blockEntity.isBlocked(sound)).setSize(Size.of(23.5f, 9)).setParent(soundsList);
            toggle.setOnMouseClicked((w, x, y, btn) -> {
                boolean value = ((WToggle) w).getToggleState();
                this.blockEntity.setBlocked(sound, value);
            });

            soundsList.addRow(toggle, soundName);
        }

        return soundsList;
    }

    public SoundMufflerContainerScreen(SoundMufflerContainer container) {
        super(container.name, container, container.player);

        this.blockEntity = container.blockEntity;

        WInterface mainInterface = getInterface();
        WPanel mainPanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(18 * 18, 10 * 18))
                .setParent(mainInterface);

        mainPanel.setLabel(container.name);
        mainPanel.setOnAlign(WAbstractWidget::center);
        mainPanel.center();
        mainInterface.add(mainPanel);

        WVerticalScrollableContainer soundsList = this.createSoundsList(mainPanel);

        WTextField searchField = new WTextField()
                .setPosition(Position.of(mainPanel, 1 * 18, 8 * 18, 0))
                .setSize(Size.of(6 * 18, 1 * 18))
                .setOnCharTyped((w, c, k) -> {
                    this.filter = w.getText();
                    // mainPanel.remove(soundsList);
                    // this.createSoundsList(mainPanel);
                });

        WStaticText rangeText = new WStaticText()
                .setText("Range:")
                .setPosition(Position.of(mainPanel, 11 * 18, 8 * 18 + 9, 0))
                .setSize(Size.of(2 * 18, 9));

        WHorizontalSlider rangeSlider = new WHorizontalSlider()
                .setMin(0).setMax(32).setProgress(this.blockEntity.getRange())
                .setPosition(Position.of(mainPanel, 13 * 18, 8 * 18, 0))
                .setSize(Size.of(4 * 18, 9));
        rangeSlider.setOnProgressChange(w -> this.blockEntity.setRange((int) w.getProgress()));

        mainPanel.add(searchField, rangeText, rangeSlider);
    }
}
