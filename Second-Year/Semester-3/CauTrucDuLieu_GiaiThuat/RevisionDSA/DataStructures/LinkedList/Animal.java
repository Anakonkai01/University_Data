abstract public class Animal {
    public void sound(){
        System.out.println("con cac");
    }

    public void run(){
        System.out.println("runnn");
    }

    public static void main(String[] args) {
        Animal a = new Dog();
        Animal b = new Cat();

        Cat cat = (Cat) b;
        
        cat.fuck();
}


class Dog extends Animal{

    @Override
    public void sound(){
        System.out.println("gau gau");
    }

}

class Cat extends Animal{

    @Override
    public void sound(){
        System.out.println("meo meo");
    }
    
    public void fuck(){
        System.out.println("yeay hayadsaskdjfkla");
    }
}

class Nhut extends Animal{

    @Override
    public void sound(){
        System.out.println("them cac quaaaa");
    }
}


