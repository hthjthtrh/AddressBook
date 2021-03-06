//@@author hthjthtrh
package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;

/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupCard extends UiPart<Region> {
    private static final String FXML = "GroupListCard.fxml";

    public final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label grpName;
    @FXML
    private Label firstPerson;
    @FXML
    private Label secondPerson;
    @FXML
    private Label thirdPerson;
    @FXML
    private Label ellipsis;

    public GroupCard(Group group, int displayedIndex) {
        super(FXML);
        this.group = group;
        id.setText(displayedIndex + ". ");
        initEllipsis();
        bindPreview(group);
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Group} properties
     * so that they will be notified of any changes.
     */
    private void bindPreview(Group group) {
        grpName.textProperty().bind(Bindings.convert(group.grpNameProperty()));
        firstPerson.textProperty().bind(Bindings.convert(group.firstPreviewProperty()));
        secondPerson.textProperty().bind(Bindings.convert(group.secondPreviewProperty()));
        thirdPerson.textProperty().bind(Bindings.convert(group.thirdPreviewProperty()));
        group.thirdPreviewProperty().addListener(((observable, oldValue, newValue) -> {
            ellipsis.setVisible(!newValue.equals(""));
        }));
    }

    /**
     * Initiating content and visibility of ellipsis
     */
    private void initEllipsis() {
        ellipsis.setText("...");
        ellipsis.setVisible(!group.thirdPreviewProperty().get().equals(""));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCard)) {
            return false;
        }

        // state check
        GroupCard card = (GroupCard) other;
        return id.getText().equals(card.id.getText())
                && group.equals(card.group);
    }
}
//@@author
