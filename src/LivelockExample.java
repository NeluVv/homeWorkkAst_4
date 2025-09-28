public class LivelockExample {
    static class Spoon {
        private Person owner;
        public Spoon(Person p) { owner = p; }
        public Person getOwner() { return owner; }
        public synchronized void setOwner(Person p) { owner = p; }
        public synchronized void use() {
            System.out.println(owner.name + " ест!");
        }
    }

    static class Person {
        private final String name;
        private boolean isHungry = true;
        public Person(String name) { this.name = name; }

        public void eatWith(Spoon spoon, Person spouse) {
            while (isHungry) {
                if (spoon.getOwner() != this) {
                    try { Thread.sleep(1); } catch (InterruptedException e) {}
                    continue;
                }
                if (spouse.isHungry) {
                    System.out.println(name + ": Дорогой(ая), ты ешь первым(ая)!");
                    spoon.setOwner(spouse);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + ": Я наелся!");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        final Person husband = new Person("Муж");
        final Person wife = new Person("Жена");
        final Spoon spoon = new Spoon(husband);

        new Thread(() -> husband.eatWith(spoon, wife)).start();
        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }
}