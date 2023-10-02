import javax.swing.JOptionPane;
import java.util.ArrayList;

public class AddressBookApp {
    private ArrayList<Contact> contacts;

    public AddressBookApp() {
        contacts = new ArrayList<>();
    }

    private void displayMenu() {
        String[] options = { "Add Contact", "Display Contacts", "Search Contact", "Delete Contact", "Exit" };
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Address Book", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                addContact();
                break;
            case 1:
                displayContacts();
                break;
            case 2:
                searchContact();
                break;
            case 3:
                deleteContact();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Exiting Address Book. Goodbye!");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                displayMenu();
                break;
        }
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog("Enter contact name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter contact phone number:");
        String email = JOptionPane.showInputDialog("Enter contact email:");

        Contact newContact = new Contact(name, phoneNumber, email);
        contacts.add(newContact);

        JOptionPane.showMessageDialog(null, "Contact added successfully!");

        displayMenu();
    }

    private void displayContacts() {
        StringBuilder contactsList = new StringBuilder();

        if (contacts.isEmpty()) {
            contactsList.append("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                contactsList.append("Name: ").append(contact.getName()).append("\n");
                contactsList.append("Phone Number: ").append(contact.getPhoneNumber()).append("\n");
                contactsList.append("Email: ").append(contact.getEmail()).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, contactsList.toString());

        displayMenu();
    }

    private void searchContact() {
        String searchName = JOptionPane.showInputDialog("Enter name to search:");

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                StringBuilder contactDetails = new StringBuilder();
                contactDetails.append("Name: ").append(contact.getName()).append("\n");
                contactDetails.append("Phone Number: ").append(contact.getPhoneNumber()).append("\n");
                contactDetails.append("Email: ").append(contact.getEmail()).append("\n");

                JOptionPane.showMessageDialog(null, contactDetails.toString());
                displayMenu();
                return;
            }
        }

        JOptionPane.showMessageDialog(null,"Contact not found.");

        displayMenu();
    }

    private void deleteContact() {
        String deleteName = JOptionPane.showInputDialog("Enter name to delete:");

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(deleteName)) {
                contacts.remove(i);

                JOptionPane.showMessageDialog(null, "Contact deleted successfully!");

                displayMenu();
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Contact not found.");

        displayMenu();
    }

    public static void main(String[] args) {
        AddressBookApp app = new AddressBookApp();
        app.displayMenu();
    }
}

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
