import java.util.*;

public class DIYarrayList <T> implements List <T>{
    private Object [] elements;
    private int size=0;

    public DIYarrayList(int i) {
        elements=new Object[i];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new  UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (elements.length==size){
            elements=Arrays.copyOf(elements,elements.length+1);
        }
        elements [size]=t;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        Object temp=elements [index];
        elements [index]=element;
        return (T) temp;
    }

    @Override
    public void add(int index, T element) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        //throw new  UnsupportedOperationException();
        return new ListItr(0);

    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private class ListItr extends Itr implements ListIterator {


        ListItr (int index){
            super();
            cursor=index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor!=0;
        }

        @Override
        public Object previous() {
            return elements[cursor-1];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return lastRet;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {
            //throw new  UnsupportedOperationException();
            DIYarrayList.this.set(lastRet, (T) o);
        }

        @Override
        public void add(Object o) {

        }
    }

    private class Itr implements Iterator {
        int cursor;
        int lastRet;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            cursor = i+ 1;
            return elements[lastRet = i];
        }

    }





}
