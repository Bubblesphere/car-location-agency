package ui.events;

public class EventEnum {
	public static enum ListEvents implements IEventName {
		LIST_VALUE_CHANGED, 
		BUTTON_ADD_CLICKED
	}
	
	public static enum TabFormListEvents implements IEventName {
	    BUTTON_ADD_CLICKED, 
	    BUTTON_SAVE_CLICKED_MODIFY,
	    BUTTON_SAVE_CLICKED_NEW,
	    LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY,
	    LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW
	}
	
	public enum FormEvents implements IEventName {
		BUTTON_SAVE_CLICKED
	}
	
	public enum FormTextFieldEvents implements IEventName {
		TEXTFIELD_TEXT_CHANGED
	}
	
	public static enum FormComboBoxEvents implements IEventName {
		COMBO_BOX_OPENED, 
		COMBO_BOX_CLOSED, 
		COMBO_BOX_CANCELLED 
	}
	
  	public static enum FormButtonEvents implements IEventName {
  		BUTTON_CLICKED
  	}
  	
  	public static enum FormPayButtonEvents implements IEventName {
  		DIALOG_OPENED,
  		DIALOG_CLOSED,
  		PAYED
  	}
}
