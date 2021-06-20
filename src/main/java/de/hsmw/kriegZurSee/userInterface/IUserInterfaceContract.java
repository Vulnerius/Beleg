package de.hsmw.kriegZurSee.userInterface;

import de.hsmw.kriegZurSee.constants.ID;

public interface IUserInterfaceContract {
    interface EventListener{
        void onFieldInput(ID id, int x, int y);
        void onDialogShow();
    }
    interface View{
        void setListener(IUserInterfaceContract.EventListener listener);
       // void updateField(KriegZurSee game);
        void showDialog(String message);
        void showError(String message);
    }
}
