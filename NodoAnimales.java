import java.util.Scanner;

public class NodoAnimales {
    static class Node {
        String questionOrAnimal;
        Node yesNode;
        Node noNode;

        Node(String questionOrAnimal) {
            this.questionOrAnimal = questionOrAnimal;
        }
    }

    private Node root;

    public NodoAnimales() {
        root = new Node("¿Estás pensando en un animal?");
        root.yesNode = new Node("¿Es un pájaro?");
        root.noNode = new Node("No puedo adivinar. ¿Qué animal era?");
        root.yesNode.yesNode = new Node("perro");
        root.yesNode.noNode = new Node("gato");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Node current = root;

        while (true) {
            while (current.yesNode != null && current.noNode != null) {
                System.out.println(current.questionOrAnimal);
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("sí") || response.equals("si")) {
                    current = current.yesNode;
                } else {
                    current = current.noNode;
                }
            }

            // Guessing the animal
            System.out.println("¿Es " + current.questionOrAnimal + "?");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("sí") || response.equals("si")) {
                System.out.println("¡Adiviné!");
                break;
            } else {
                System.out.println("No pude adivinar. ¿Qué animal era?");
                String animal = scanner.nextLine().trim();
                System.out.println("¿Qué pregunta distinguiría a " + animal + " de " + current.questionOrAnimal + "?");
                String newQuestion = scanner.nextLine().trim();
                
                Node newAnimalNode = new Node(animal);
                Node currentAnimalNode = new Node(current.questionOrAnimal);
                
                current.questionOrAnimal = newQuestion;
                System.out.println("Si el animal fuera " + animal + ", ¿cuál sería la respuesta? (sí/no)");
                response = scanner.nextLine().trim().toLowerCase();
                
                if (response.equals("sí") || response.equals("si")) {
                    current.yesNode = newAnimalNode;
                    current.noNode = currentAnimalNode;
                } else {
                    current.noNode = newAnimalNode;
                    current.yesNode = currentAnimalNode;
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        NodoAnimales animalTree = new NodoAnimales();
        animalTree.play();
    }
}