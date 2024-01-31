import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner me = new Scanner(System.in);
        System.out.println("Enter the number of links");
        int links = me.nextInt();
        me.nextLine();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < links; i++) {
            System.out.println("Enter the start");
            String start = me.nextLine();
            System.out.println("Enter the end");
            String end = me.nextLine();
            System.out.println("Enter the weight");
            int weight = me.nextInt();
            me.nextLine();
            edges.add(new Edge(start, end, weight));
        }

        Graph g = new Graph(edges);
        System.out.println("Enter the start vertex for search");
        String v=me.nextLine();
        g.bfs(v);
        g.printGraph();
    }
}

class Edge {
    String start, end;
    int weight;

    Edge(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

class Graph {
    static HashMap<String, Integer> vertex = new HashMap<>();
    List<List<Node>> temp = new ArrayList<>();

    Graph(List<Edge> edges) {
        int ind = 0;
        for (Edge edge : edges) {
            if (!vertex.containsKey(edge.start)) {
                vertex.put(edge.start, ind++);
            }
            if (!vertex.containsKey(edge.end)) {
                vertex.put(edge.end, ind++);
            }
        }
        for (int i = 0; i < ind; i++) {
            temp.add(new ArrayList<>());
        }
        for (Edge e : edges) {
            temp.get(vertex.get(e.start)).add(new Node(e.end, e.weight));
        }
    }

    void printGraph() {
        System.out.println();
        for (Map.Entry<String, Integer> entry : vertex.entrySet()) {
            String vertexName = entry.getKey();
            int vertexIndex = entry.getValue();
            System.out.print(vertexName + " -> ");
            for (Node node : temp.get(vertexIndex)) {
                System.out.print("(" + node.end + ", " + node.weight + ") ");
            }
            System.out.println();
        }
    }

    static class Node {
        String end;
        int weight;

        Node(String end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    public void bfs(String startVertex) {
        List<String> visited = new ArrayList<>();
        Queue<String> search = new LinkedList<>();
        search.add(startVertex);
        while (!search.isEmpty()) {
            String vert = search.poll();
            System.out.print(vert + " ");
            visited.add(vert);
            int ind = vertex.get(vert);
            for (Node node : temp.get(ind)) {
                String k = node.end;
                if (!visited.contains(k)) {
                    search.add(k);
                    visited.add(k);
                }
            }
        }
    }
}
