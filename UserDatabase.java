// Организовать вводи и хранение данных пользователей. ФИО возраст и пол
// вывод в формате Фамилия И.О. возраст пол
// добавить возможность выхода или вывода списка отсортированного по возрасту!)
// *реализовать сортировку по возрасту с использованием индексов
// *реализовать сортировку по возрасту и полу с использованием индексов
// так же дополнительно реализовано удаление из базы пользователей по индексу
import java.util.*;

public class UserDatabase {
    private List<User> userList;
    private List<Integer> ageIndex;
    private List<Integer> genderIndex;

    public UserDatabase() {
        userList = new ArrayList<>();
        ageIndex = new ArrayList<>();
        genderIndex = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
        ageIndex.add(userList.size() - 1);
        genderIndex.add(userList.size() - 1);
        sortIndexes();
    }

    public void removeUser(int index) {
        userList.remove(index);
        ageIndex.clear();
        genderIndex.clear();
        for (int i = 0; i < userList.size(); i++) {
            ageIndex.add(i);
            genderIndex.add(i);
        }
        sortIndexes();
    }

    public void printUserList() {
        System.out.println("User list:");
        for (User user : userList) {
            System.out.println(user.getLastName() + " " + user.getInitials() + " " + user.getAge() + " " + user.getGender());
        }
    }

    public void printSortedByAge() {
        System.out.println("User list sorted by age:");
        for (int index : ageIndex) {
            User user = userList.get(index);
            System.out.println(user.getLastName() + " " + user.getInitials() + " " + user.getAge() + " " + user.getGender());
        }
    }

    public void printSortedByAgeAndGender() {
        System.out.println("User list sorted by age and gender:");
        for (int index : genderIndex) {
            User user = userList.get(index);
            System.out.println(user.getLastName() + " " + user.getInitials() + " " + user.getAge() + " " + user.getGender());
        }
    }

    private void sortIndexes() {
        Collections.sort(ageIndex, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return userList.get(i1).getAge() - userList.get(i2).getAge();
            }
        });
        Collections.sort(genderIndex, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                User u1 = userList.get(i1);
                User u2 = userList.get(i2);
                int result = u1.getAge() - u2.getAge();
                if (result == 0) {
                    result = u1.getGender().compareTo(u2.getGender());
                }
                return result;
            }
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDatabase database = new UserDatabase();

        while (true) {
            System.out.println("1. Add user");
            System.out.println("2. Remove user");
            System.out.println("3. Print user list");
            System.out.println("4. Print user list sorted by age");
            System.out.println("5. Print user list sorted by age and gender");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter last name: ");
                    String lastName = scanner.next();
                    System.out.print("Enter first name: ");
                    String firstName = scanner.next();
                    System.out.print("Enter middle name: ");
                    String middleName = scanner.next();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter gender (M/F): ");
                    String gender = scanner.next();
                    User user = new User(lastName, firstName, middleName, age, gender);
                    database.addUser(user);
                    System.out.println("User added successfully!");
                    break;
                case 2:
                    System.out.print("Enter index of user to remove: ");
                    int index = scanner.nextInt();
                    if (index < 0 || index >= database.userList.size()) {
                        System.out.println("Invalid index, try again.");
                        break;
                    }
                    database.removeUser(index);
                    System.out.println("User removed successfully!");
                    break;
                case 3:
                    database.printUserList();
                    break;
                case 4:
                    database.printSortedByAge();
                    break;
                case 5:
                    database.printSortedByAgeAndGender();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }
}

class User {
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private String gender;

    public User(String lastName, String firstName, String middleName, int age, String gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getInitials() {
        String initials = firstName.substring(0, 1).toUpperCase() + ".";
        if (!middleName.isEmpty()) {
            initials += middleName.substring(0, 1).toUpperCase() + ".";
        }
        return initials;
    }
}