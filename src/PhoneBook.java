import java.util.*;

public class PhoneBook {

    //Method to create map from list of contacts
    public Map<String, List<Contact>> getMapFromValidContacts(List<String[]> validRecordList) {
        Map<String,List<Contact>> phonebookMap = new HashMap<>();
        for (String[] record: validRecordList) {
            Contact contact = new Contact();
            contact.setColumn1(record[0]);
            contact.setColumn2(record[1]);
            contact.setColumn3(record[2]);
            contact.setColumn4(record[3]);

            String key = record[0]+" "+record[1];
            List<Contact> contacts = new ArrayList<>();
            if(phonebookMap.containsKey(key)){
                contacts = phonebookMap.get(key);
            }
            contacts.add(contact);
            phonebookMap.put(key,contacts);
        }
        return phonebookMap;
    }

    // Method to format contacts
    private List<Contact> getFormattedContacts(String s, List<Contact> contacts) {

        for (Contact contact:
                contacts) {
            if(!contact.getColumn1().equalsIgnoreCase(s)){
                String temp = contact.getColumn1();
                contact.setColumn1(contact.getColumn2());
                contact.setColumn2(temp);
            }
            if(Character.isDigit(contact.getColumn3().trim().charAt(0))){
                String temp = contact.getColumn3().trim();
                contact.setColumn3(contact.getColumn4());
                contact.setColumn4(temp);
            }
            contact.setColumn4("("+contact.getColumn4().substring(0,3)+") "+contact.getColumn4().substring(3,6)+"-"+contact.getColumn4().substring(6));
        }
        return contacts;
    }

    //Method to filter contacts by query
    private List<Contact> getContactsForQuery(String s, Map<String,List<Contact>> phonebookMap) {

        List<Contact> contacts = new ArrayList<>();

        Iterator<Map.Entry<String,List<Contact>>> iterator = phonebookMap.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String, List<Contact>> entry = iterator.next();
            if(entry.getKey().toLowerCase().contains(s.toLowerCase())){
                contacts.addAll(entry.getValue());
            }
        }
        return contacts;
    }

    //
    public StringBuilder getContactsFilteredByQuery(List<String[]> validRecordList,List<String[]> queries) {
        Map<String,List<Contact>> phoneBookMap = getMapFromValidContacts(validRecordList);
        StringBuilder builder = new StringBuilder();
        for (String[] query:
                queries) {
            builder.append("Match for: "+query[0]+"\n");
            List<Contact> contacts = getContactsForQuery(query[0],phoneBookMap);
            if(contacts.isEmpty()){
                builder.append("No results found\n");
            } else{
                Collections.sort(getFormattedContacts(query[0],contacts));
                for (int i=0;i<contacts.size();i++) {
                    Contact contact = contacts.get(i);
                    builder.append("Result "+(i+1)+": "+contact.getColumn1().trim()+", "+contact.getColumn2().trim()+", "+contact.getColumn3()+", "+contact.getColumn4()+"\n");
                }
            }
        }
        return builder;
    }
}
