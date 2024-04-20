import java.util.*;

public class Main {
    public static void main(String[] args) {
        // # 1
        MyTree tree = new MyTree(5,4);
        MyNode Center = tree.Add(null,new MyNode("0",0));
        MyNode one = tree.Add(Center, new MyNode("1",20));
        MyNode two = tree.Add(Center, new MyNode("2",10));
        MyNode three = tree.Add(Center, new MyNode("3",30));
        MyNode four = tree.Add(Center, new MyNode("4",10));
        MyNode five = tree.Add(Center, new MyNode("5",20));

        MyNode P = tree.Add(one, new MyNode("P",20));
        MyNode six = tree.Add(one, new MyNode("6",10));
        MyNode seven = tree.Add(one, new MyNode("7",10));

        MyNode eight = tree.Add(two, new MyNode("8",10));

        MyNode H = tree.Add(three, new MyNode("H",30));

        MyNode I = tree.Add(four, new MyNode("I",30));
        MyNode nine = tree.Add(four, new MyNode("9",30));

        MyNode O = tree.Add(five, new MyNode("O",20));
        MyNode eleven = tree.Add(five, new MyNode("11",30));
        MyNode M = tree.Add(five, new MyNode("M", 30));
        MyNode ten = tree.Add(five, new MyNode("10",20));

        MyNode A = tree.Add(six, new MyNode("A",10));
        MyNode B = tree.Add(six, new MyNode("B",10));

        MyNode twelve = tree.Add(seven, new MyNode("12",10));
        MyNode D = tree.Add(seven, new MyNode("D",30));

        MyNode E = tree.Add(eight, new MyNode("E",30));
        MyNode F = tree.Add(eight, new MyNode("F",30));
        MyNode G = tree.Add(eight, new MyNode("G",10));

        MyNode J = tree.Add(nine, new MyNode("J",30));

        MyNode N = tree.Add(eleven,new MyNode("N",10));

        MyNode thirteen = tree.Add(ten, new MyNode("13",10));
        MyNode L = tree.Add(ten, new MyNode("L",20));

        MyNode C = tree.Add(twelve, new MyNode("C",30));

        MyNode K = tree.Add(thirteen, new MyNode("K",10));

        // # 2
        traversal(tree,tree.root());

        List<MyNode> goalList = goalWeight.keySet().stream().sorted((Comparator.comparingInt(e -> goalWeight.get(e)))).toList();
        List<MyNode> edgeList = goalEdge.keySet().stream().sorted((Comparator.comparingInt(e -> goalEdge.get(e)))).toList();
        int idx = 0;
        final MyNode node1 = goalList.get(idx);
        List<MyNode> list1 = goalList.stream().filter(n -> goalWeight.get(n).equals(goalWeight.get(node1))).toList();
        idx = list1.size();
        final MyNode node2 = goalList.get(idx);
        List<MyNode> list2 = goalList.stream().filter(n -> goalWeight.get(n).equals(goalWeight.get(node2))).toList();

        System.out.println("가장 저렴한 비용 이동: "+getString(list1,list2));

        idx = goalList.size()-1;
        final MyNode node3 = goalList.get(idx);
        list1 = goalList.stream().filter(n -> goalWeight.get(n).equals(goalWeight.get(node3))).toList();
        idx -= list1.size();
        final MyNode node4 = goalList.get(idx);
        list2 = goalList.stream().filter(n -> goalWeight.get(n).equals(goalWeight.get(node4))).toList();

        System.out.println("가장 비싼 비용 이동: "+getString(list1,list2));

        idx = 0;
        final MyNode node5 = edgeList.get(idx);
        list1 = edgeList.stream().filter(n -> goalEdge.get(n).equals(goalEdge.get(node5))).toList();
        idx = list1.size();
        final MyNode node6 = edgeList.get(idx);
        list2 = edgeList.stream().filter(n -> goalEdge.get(n).equals(goalEdge.get(node6))).toList();

        System.out.println("최소 교차로를 거쳐 이동: "+getString(list1,list2));

        idx = edgeList.size()-1;
        final MyNode node7 = edgeList.get(idx);
        list1 = edgeList.stream().filter(n -> goalEdge.get(n).equals(goalEdge.get(node7))).toList();
        idx -= list1.size();
        final MyNode node8 = edgeList.get(idx);
        list2 = edgeList.stream().filter(n -> goalEdge.get(n).equals(goalEdge.get(node8))).toList();

        System.out.println("최대 교차로를 거쳐 이동: "+getString(list1,list2));

        traversal2(tree,tree.root());
        // # 3
        List<MyNode> middleEdgeList = wayCostMap.keySet().stream().sorted((Comparator.comparingInt(e -> wayCostMap.get(e)))).toList();
        MyNode expensiveNode = middleEdgeList.get(middleEdgeList.size()-2);
        System.out.println("비용의 합이 가장 큰 교차로 : ["+expensiveNode.Name+","+tree.parent(expensiveNode).Name+"]");
    }

    public static String getString(List<MyNode> list1, List<MyNode> list2){
        String cheapCost = "";
        if(list1.size() > 1){
            for(int i = 0; i<list1.size()-1; i++){
                for(int j = 1; j<list1.size(); j++){
                    cheapCost += "["+list1.get(i).Name+","+list1.get(j).Name+"]";
                    if(!(i==list1.size()-2 && j == list1.size()-1)){
                        cheapCost+=",";
                    }
                }
            }
        }
        else{
            for(int i = 0; i<list1.size(); i++){
                for(int j = 0; j<list2.size(); j++){
                    cheapCost += "["+list1.get(i).Name+","+list2.get(j).Name+"]";
                    if(!(i==list1.size()-1 && j == list2.size()-1)){
                        cheapCost+=",";
                    }
                }
            }
        }

        return cheapCost;
    }
    static Map<MyNode,Integer> goalWeight = new HashMap<>();
    static Map<MyNode,Integer> goalEdge = new HashMap<>();

    static Map<MyNode,Integer> middleWeight = new HashMap<>();
    static Map<MyNode,Integer> middleEdge = new HashMap<>();
    public static void traversal(MyTree tree, MyNode node){
        if(node == null){
            return;
        }
        boolean isGoal = false;
        int n;
        try{
            n = Integer.parseInt(node.Name);
        }
        catch(NumberFormatException e){
            isGoal = true;
        }
        if(isGoal){
            if(middleWeight.containsKey(tree.parent(node))){
                goalWeight.put(node,node.Value+middleWeight.get(tree.parent(node)));
                goalEdge.put(node,1+middleEdge.get(tree.parent(node)));
            }
            else{
                goalWeight.put(node,node.Value);
                goalEdge.put(node,0);
            }
        }
        else{
            if(middleWeight.containsKey(tree.parent(node))){
                middleWeight.put(node,node.Value+middleWeight.get(tree.parent(node)));
                middleEdge.put(node,1+middleEdge.get(tree.parent(node)));
            }
            else{
                middleWeight.put(node,node.Value);
                middleEdge.put(node,0);
            }
        }
        for(MyNode v : tree.children(node)){
            traversal(tree,v);
        }
    }
    static Map<MyNode,Integer> wayCostMap = new HashMap<>();
    public static void traversal2(MyTree tree, MyNode node){
        if(node == null){
            return;
        }
        for(MyNode v : tree.children(node)){
            traversal2(tree,v);
        }
        boolean isGoal = false;
        try{
            int i = Integer.parseInt(node.Name);
        }
        catch(NumberFormatException e){
            isGoal = true;
        }
        if(isGoal){
            wayCostMap.put(node,node.Value);
        }
        MyNode parent = tree.parent(node);
        if(parent == null){
            return;
        }
        if(!wayCostMap.containsKey(parent)){
            wayCostMap.put(parent,parent.Value);
        }
        wayCostMap.put(parent,wayCostMap.get(parent)+wayCostMap.get(node));
    }
}

class MyTree{
    int Degree;
    int MaxHeight;

    ArrayList<MyNode> treeStructure;
    public MyTree(int degree, int height){
        Degree = degree;
        MaxHeight = height;
        int size = (int) Math.pow(degree,height+1)-1 / (degree - 1);

        treeStructure = new ArrayList<MyNode>();
        for(int i = 0; i<size; i++){
            treeStructure.add(null);
        }
    }
    //0 0
    //1 1 2 3 4 5
    //2 6 7 8 9 10~11~16~21~26 27 28 29 30
    //3 31
    public MyNode Add(MyNode p, MyNode n){ // 자식 추가
        if(p == null){ // Set Root
            treeStructure.set(0, n);
            return n;
        }
        int idx = treeStructure.indexOf(p);
        for(int i = idx*Degree+1; i< (idx+1)*Degree+1; i++){
            if(treeStructure.get(i) == null){
                treeStructure.set(i,n);
                return n;
            }
        }
        System.out.println("Can't not Add.");
        return null;
    }

    public int size(){
        int size = 0;
        for(MyNode n : treeStructure){
            if(n != null){
                size++;
            }
        }
        return size;
    }

    public boolean isEmpty(){
        return treeStructure.get(0) == null;
    }

    public ArrayList<MyNode> nodes(){
        return treeStructure;
    }

    public MyNode root(){
        return treeStructure.get(0);
    }

    public ArrayList<MyNode> children(MyNode v){
        int idx = treeStructure.indexOf(v);
        if(idx*Degree+1 > treeStructure.size()){
            return new ArrayList<>(0);
        }
        if((idx+1)*Degree+1 > treeStructure.size()){
            return new ArrayList<MyNode>(treeStructure.subList(idx*Degree+1, treeStructure.size()));
        }
        return new ArrayList<MyNode>(treeStructure.subList(idx*Degree+1, (idx+1)*Degree+1));
    }
    public MyNode parent(MyNode v){
        int idx = treeStructure.indexOf(v);
        int offset = (idx -1) % Degree + 1;
        return treeStructure.get((idx - offset)/Degree);
    }
    public boolean isInternal(MyNode v){
        return treeStructure.get(treeStructure.indexOf(v)*Degree+1) != null;
    }

    public boolean isExternal(MyNode v){
        return !isInternal(v);
    }

    public boolean isRoot(MyNode v){
        return treeStructure.indexOf(v) == 0;
    }

    public MyNode replace(MyNode v, String name, int value){
        MyNode n = new MyNode(name,value);
        treeStructure.set(treeStructure.indexOf(v),n);
        return n;
    }

    public int height(MyNode v){ // Height는 트리의 높이를 말하는건데 Level 물어보는듯?
        if(v == null){
            return 0;
        }
        return height(children(v).get(0));
    }
    public int level(MyNode v){
        return depth(v) + 1;
    }

    public int depth(MyNode v){
        int idx = treeStructure.indexOf(v);
        int depth = 1;
        while(idx == 0){
            idx /= Degree;
            depth ++;
        }
        return depth-1;
    }
}

class MyNode{
    String Name;
    int Value;

    public MyNode(String name, int value){
        Name = name;
        Value = value;
    }
}