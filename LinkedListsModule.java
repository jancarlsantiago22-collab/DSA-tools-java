import java.util.Scanner;

public class LinkedListsModule {
    private final Scanner sc;
    public LinkedListsModule(Scanner sc){ this.sc=sc; }

    // Singly
    static class SNode{int data; SNode next; SNode(int d){data=d;}}
    SNode sHead=null;
    // Doubly
    static class DNode{int data; DNode prev,next; DNode(int d){data=d;}}
    DNode dHead=null, dTail=null;
    // Circular singly
    static class CNode{int data; CNode next; CNode(int d){data=d;}}
    CNode cTail=null;

    public void run(){
        boolean back=false;
        while(!back){
            System.out.println("\n--- LINKED LISTS MODULE ---");
            System.out.println("1. Singly Linked List operations");
            System.out.println("2. Doubly Linked List operations");
            System.out.println("3. Circular Linked List operations");
            System.out.println("0. Return to Main Menu");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,3);
            switch(c){
                case 1 -> singlyMenu();
                case 2 -> doublyMenu();
                case 3 -> circularMenu();
                case 0 -> back=true;
            }
        }
    }

    private void singlyMenu(){
        boolean b=false;
        while(!b){
            System.out.println("\n[Singly Linked List]");
            System.out.println("1 Insert at beginning 2 Insert at end 3 Insert at position 4 Delete by value 5 Delete by position 6 Search 7 Display");
            System.out.println("0 Back");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,7);
            switch(c){
                case 1 -> { int v=ConsoleInput.readAnyInt(sc,"Value: "); SNode n=new SNode(v); n.next=sHead; sHead=n; System.out.println("Inserted at beginning."); }
                case 2 -> { int v=ConsoleInput.readAnyInt(sc,"Value: "); SNode n=new SNode(v); if(sHead==null) sHead=n; else { SNode cur=sHead; while(cur.next!=null) cur=cur.next; cur.next=n; } System.out.println("Inserted at end."); }
                case 3 -> { int pos=ConsoleInput.readAnyInt(sc,"Position (0=first): "); int v=ConsoleInput.readAnyInt(sc,"Value: "); if(pos<=0 || sHead==null){ SNode n=new SNode(v); n.next=sHead; sHead=n; } else { SNode cur=sHead; for(int i=0;i<pos-1 && cur.next!=null;i++) cur=cur.next; SNode n=new SNode(v); n.next=cur.next; cur.next=n; } System.out.println("Inserted at position."); }
                case 4 -> { int v=ConsoleInput.readAnyInt(sc,"Value to delete: "); if(sHead==null){System.out.println("Empty.");} else if(sHead.data==v){sHead=sHead.next; System.out.println("Deleted.");} else {SNode cur=sHead; while(cur.next!=null && cur.next.data!=v) cur=cur.next; if(cur.next!=null){cur.next=cur.next.next; System.out.println("Deleted.");} else System.out.println("Not found.");}}
                case 5 -> { if(sHead==null){System.out.println("Empty."); return;} int pos=ConsoleInput.readAnyInt(sc,"Position to delete: "); if(pos<=0){sHead=sHead.next;} else {SNode cur=sHead; for(int i=0;i<pos-1 && cur.next!=null;i++) cur=cur.next; if(cur.next!=null) cur.next=cur.next.next; } System.out.println("Deleted at position if existed."); }
                case 6 -> { int v=ConsoleInput.readAnyInt(sc,"Search value: "); int idx=0; SNode cur=sHead; while(cur!=null){ if(cur.data==v){ System.out.println("Found at index "+idx); break; } cur=cur.next; idx++; } if(cur==null) System.out.println("Not found."); }
                case 7 -> { if(sHead==null){System.out.println("Empty.");} else {SNode cur=sHead; System.out.print("SLL: "); while(cur!=null){ System.out.print(cur.data+" -> "); cur=cur.next; } System.out.println("null"); } }
                case 0 -> b=true;
            }
        }
    }

    private void doublyMenu(){
        boolean b=false;
        while(!b){
            System.out.println("\n[Doubly Linked List - Forward & Reverse Display]");
            System.out.println("1 Insert at beginning 2 Insert at end 3 Delete by value 4 Display forward 5 Display reverse 6 Search 0 Back");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,6);
            switch(c){
                case 1 -> { int v=ConsoleInput.readAnyInt(sc,"Value: ");
                 DNode n=new DNode(v); 
                 if(dHead==null){dHead=n; dTail=n;} 
                    else {n.next=dHead; dHead.prev=n; dHead=n;} 
                        System.out.println("Inserted."); 
                    }
                case 2 -> { int v=ConsoleInput.readAnyInt(sc,"Value: "); DNode n=new DNode(v); 
                if(dTail==null){dHead=n; dTail=n;} 
                else {dTail.next=n; n.prev=dTail; dTail=n;} 
                System.out.println("Inserted at end."); 
                }

                case 3 -> { int v=ConsoleInput.readAnyInt(sc,"Value to delete: "); DNode cur=dHead; 
                while(cur!=null && cur.data!=v) cur=cur.next; 
                if(cur==null) 
                    System.out.println("Not found."); 
                else { 
                if(cur.prev!=null) cur.prev.next=cur.next; 
                else dHead=cur.next; 
                    if(cur.next!=null) cur.next.prev=cur.prev; else dTail=cur.prev; 
                    System.out.println("Deleted."); } 
                }

                case 4 -> { 
                    if(dHead==null) System.out.println("Empty."); 
                    else { DNode cur=dHead; System.out.print("DLL Forward: "); 
                    
                        while(cur!=null){ System.out.print(cur.data+" <-> "); cur=cur.next; } System.out.println("null"); } }
                case 5 -> { if(dTail==null) System.out.println("Empty."); 
                else { DNode cur=dTail; System.out.print("DLL Reverse: "); while(cur!=null){ 
                    System.out.print(cur.data+" <-> "); cur=cur.prev; } System.out.println("null"); } 
                }
                case 6 -> { int v=ConsoleInput.readAnyInt(sc,"Search value: "); DNode cur=dHead; int idx=0; 
                while(cur!=null){ if(cur.data==v){ 
                    System.out.println("Found at index "+idx); 
                    break; } cur=cur.next; idx++; 
                } 
                    if(cur==null) 
                        System.out.println("Not found.");
                     }
                case 0 -> b=true;
            }
        }
    }

    private void circularMenu(){
        boolean b=false;
        while(!b){
            System.out.println("\n[Circular Linked List]");
            System.out.println("1 Insert at end 2 Insert at beginning 3 Delete by value 4 Display 5 Search 0 Back");
            int c=ConsoleInput.readInt(sc,"Choice: ",0,5);
            switch(c){
                case 1 -> { int v=ConsoleInput.readAnyInt(sc,"Value: "); CNode n=new CNode(v); if(cTail==null){cTail=n; n.next=n;} else {n.next=cTail.next; cTail.next=n; cTail=n;} System.out.println("Inserted."); }
                case 2 -> { int v=ConsoleInput.readAnyInt(sc,"Value: "); CNode n=new CNode(v); if(cTail==null){cTail=n; n.next=n;} else {n.next=cTail.next; cTail.next=n;} System.out.println("Inserted at beginning."); }
                case 3 -> { if(cTail==null){System.out.println("Empty."); break;} int v=ConsoleInput.readAnyInt(sc,"Value to delete: "); CNode cur=cTail.next, prev=cTail; boolean found=false; do{ if(cur.data==v){ found=true; if(cur==prev){cTail=null;} else {prev.next=cur.next; if(cur==cTail) cTail=prev;} System.out.println("Deleted."); break; } prev=cur; cur=cur.next; } while(prev!=cTail); if(!found) System.out.println("Not found."); }
                case 4 -> { if(cTail==null){System.out.println("Empty.");} else { CNode cur=cTail.next; System.out.print("CLL: "); do{ System.out.print(cur.data+" -> "); cur=cur.next; } while(cur!=cTail.next); System.out.println("(back to head)"); } }
                case 5 -> { if(cTail==null){System.out.println("Empty.");} else { int v=ConsoleInput.readAnyInt(sc,"Search value: "); CNode cur=cTail.next; int idx=0; boolean f=false; do{ if(cur.data==v){ System.out.println("Found at index "+idx); f=true; break; } cur=cur.next; idx++; } while(cur!=cTail.next); if(!f) System.out.println("Not found."); } }
                case 0 -> b=true;
            }
        }
    }
}
