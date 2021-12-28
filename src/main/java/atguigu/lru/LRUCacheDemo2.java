package atguigu.lru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LRUCacheDemo2 {

    //1. 构建一个Node节点,作为数据载体
    class Node<K,V>
    {
        K key;
        V value;
        Node<K,V> pre;
        Node<K,V> next;

        public Node(){
            this.pre = this.next = null;
        }

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.pre = this.next = null;
        }
    }

    //2. 构建一个虚拟的双向链表,里面安放的就是我们的node
    class DoubleLinkedList<K,V>
    {
        Node<K,V> head;
        Node<K,V> tail;

        //2.1 构造方法
        public DoubleLinkedList(){
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        //2.2 添加到头
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        //2.3 删除节点
        public void removeNode(Node<K,V> node){
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node.pre = null;
            node.next = null;
        }

        //2.4 获得最后一个节点
        public Node getLast(){
            return tail.pre;
        }
    }

    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCacheDemo2(int cacheSize){
        this.cacheSize = cacheSize;
        map = new HashMap<Integer, Node<Integer, Integer>>();
        doubleLinkedList = new DoubleLinkedList<Integer, Integer>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }

        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else {
            if(map.size() == cacheSize){//满了
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            //新增
            Node<Integer, Integer> newNode = new Node(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }


    public static void main(String[] args) {
        LRUCacheDemo2 lruCacheDemo = new LRUCacheDemo2(3);
        lruCacheDemo.put(1, 1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);
        lruCacheDemo.put(4, 4);
        lruCacheDemo.put(3,3);

        DoubleLinkedList<Integer, Integer> doubleLinkedList = lruCacheDemo.doubleLinkedList;
        System.out.println(doubleLinkedList.head.next.key);
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("");
    }


}
