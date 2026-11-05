import java.util.Scanner;
import java.util.Stack;

public class TreesModule {
    private final Scanner sc;
    public TreesModule(Scanner sc){ this.sc=sc; }

    // BST
    static class BSTNode{int data; BSTNode left,right; BSTNode(int d){data=d;}}
    BSTNode root=null;

    // Expression Tree
    static class ETNode{String val; ETNode left,right; ETNode(String v){val=v;}}

    public void run(){
        boolean back=false;
        while(!back){
            System.out.println("\n--- TREES MODULE ---");
            System.out.println("[Binary Search Tree]");
            System.out.println("1. Insert BST  2. Search BST  3. Delete BST  4. Traversals (Inorder/Preorder/Postorder)");
            System.out.println("[Expression Tree]");
            System.out.println("5. Build Expression Tree from Postfix");
            System.out.println("6. Display Prefix/Infix/Postfix from Expression Tree & Evaluate");
            System.out.println("0. Return to Main Menu");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,6);
            switch(c){
                case 1 -> insertBST();
                case 2 -> searchBST();
                case 3 -> deleteBST();
                case 4 -> traverseBST();
                case 5 -> buildExpTree();
                case 6 -> evalExpTree();
                case 0 -> back=true;
            }
        }
    }
    private void insertBST(){
        int v=ConsoleInput.readAnyInt(sc,"Value to insert into BST: ");
        root=insertRec(root,v);
        System.out.println("Inserted "+v+" into BST.");
    }
    private BSTNode insertRec(BSTNode node,int v){
        if(node==null) return new BSTNode(v);
        if(v<node.data) node.left=insertRec(node.left,v);
        else if(v>node.data) node.right=insertRec(node.right,v);
        else System.out.println("Duplicate not inserted (BST invariant).");
        return node;
    }
    private void searchBST(){
        int v=ConsoleInput.readAnyInt(sc,"Value to search: ");
        boolean found=searchRec(root,v);
        System.out.println(found? "Found "+v+" in BST." : "Not found.");
    }
    private boolean searchRec(BSTNode node,int v){
        if(node==null) return false;
        if(node.data==v) return true;
        return v<node.data ? searchRec(node.left,v) : searchRec(node.right,v);
    }
    private void deleteBST(){
        int v=ConsoleInput.readAnyInt(sc,"Value to delete: ");
        root=deleteRec(root,v);
    }
    private BSTNode deleteRec(BSTNode node,int v){
        if(node==null){ System.out.println("Not found."); return null; }
        if(v<node.data) node.left=deleteRec(node.left,v);
        else if(v>node.data) node.right=deleteRec(node.right,v);
        else {
            System.out.println("Deleting "+v);
            if(node.left==null) return node.right;
            if(node.right==null) return node.left;
            node.data=minValue(node.right);
            node.right=deleteRec(node.right,node.data);
        }
        return node;
    }
    private int minValue(BSTNode node){ int min=node.data; while(node.left!=null){ min=node.left.data; node=node.left; } return min; }
    private void traverseBST(){
        if(root==null){ System.out.println("BST empty."); return; }
        System.out.print("Inorder (sorted): "); inorder(root); System.out.println();
        System.out.print("Preorder: "); preorder(root); System.out.println();
        System.out.print("Postorder: "); postorder(root); System.out.println();
    }
    private void inorder(BSTNode n){ if(n!=null){ inorder(n.left); System.out.print(n.data+" "); inorder(n.right); } }
    private void preorder(BSTNode n){ if(n!=null){ System.out.print(n.data+" "); preorder(n.left); preorder(n.right); } }
    private void postorder(BSTNode n){ if(n!=null){ postorder(n.left); postorder(n.right); System.out.print(n.data+" "); } }

    // Expression tree
    ETNode expRoot=null;
    private void buildExpTree(){
        String postfix=ConsoleInput.readNonEmpty(sc,"Enter postfix expression (e.g. ab+cd-* or 23*5+ ): ");
        try{
            Stack<ETNode> st=new Stack<>();
            for(char ch: postfix.toCharArray()){
                if(Character.isWhitespace(ch)) continue;
                String token=String.valueOf(ch);
                if(isOperator(token)){
                    if(st.size()<2){ System.out.println("Invalid postfix."); return; }
                    ETNode right=st.pop(); ETNode left=st.pop();
                    ETNode node=new ETNode(token); node.left=left; node.right=right; st.push(node);
                } else {
                    st.push(new ETNode(token));
                }
            }
            if(st.size()!=1){ System.out.println("Invalid postfix expression."); return; }
            expRoot=st.pop();
            System.out.println("Expression tree built.");
        } catch(Exception e){ System.out.println("Error: "+e.getMessage()); }
    }
    private void evalExpTree(){
        if(expRoot==null){ System.out.println("Build tree first (option 5)."); return; }
        System.out.print("Prefix (Preorder): "); preExp(expRoot); System.out.println();
        System.out.print("Infix (Inorder with parens): "); inExp(expRoot); System.out.println();
        System.out.print("Postfix (Postorder): "); postExp(expRoot); System.out.println();
        // try numeric evaluation
        try{
            double result=evalNumeric(expRoot);
            System.out.println("Evaluated result (numeric only): "+result);
        } catch(Exception e){
            System.out.println("Evaluation skipped - tree contains variables (non-numeric).");
        }
    }
    private boolean isOperator(String t){ return t.equals("+")||t.equals("-")||t.equals("*")||t.equals("/")||t.equals("^"); }
    private void preExp(ETNode n){ if(n!=null){ System.out.print(n.val+" "); preExp(n.left); preExp(n.right); } }
    private void inExp(ETNode n){ if(n!=null){ if(isOperator(n.val)) System.out.print("("); inExp(n.left); System.out.print(n.val); inExp(n.right); if(isOperator(n.val)) System.out.print(")"); } }
    private void postExp(ETNode n){ if(n!=null){ postExp(n.left); postExp(n.right); System.out.print(n.val+" "); } }
    private double evalNumeric(ETNode n){
        if(n==null) return 0;
        if(!isOperator(n.val)) return Double.parseDouble(n.val);
        double l=evalNumeric(n.left); double r=evalNumeric(n.right);
        return switch(n.val){ case "+" -> l+r; case "-" -> l-r; case "*" -> l*r; case "/" -> l/r; case "^" -> Math.pow(l,r); default -> 0; };
    }
}
