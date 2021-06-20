package de.hsmw.kriegZurSee.userInterface.controlling;

import de.hsmw.kriegZurSee.App;
import de.hsmw.kriegZurSee.constants.ID;
import de.hsmw.kriegZurSee.constants.Messages;
import de.hsmw.kriegZurSee.userInterface.IUserInterfaceContract;
import de.hsmw.kriegZurSee.userInterface.UserInterFace;


public class ControlLogic implements IUserInterfaceContract.EventListener {

   // private final IStorage storage;
    private final IUserInterfaceContract.View view;

    public ControlLogic(/*IStorage storage, */IUserInterfaceContract.View view) {
      //  this.storage = storage;
        this.view = view;
    }

    @Override
    public void onFieldInput(ID id, int x, int y) {
        try {
            UserInterFace ui  = App.ui;
            ui.updateField(id,x,y);
        } catch (Exception e){
            e.printStackTrace();
            view.showError(Messages.Error);
        }
    }

    @Override
    public void onDialogShow() {

    }
}
