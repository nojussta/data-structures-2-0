package edu.ktu.ds.lab2.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * Rikiuojamos objektų kolekcijos - aibės realizacija dvejetainiu paieškos
 * medžiu.
 *
 * @param <E> Aibės elemento tipas. Turi tenkinti interfeisą Comparable<E>, arba
 *            per klasės konstruktorių turi būti paduodamas Comparator<E> interfeisą
 *            tenkinantis objektas.
 * @author darius.matulis@ktu.lt
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 */
public class BstSet<E extends Comparable<E>> implements SortedSet<E>, Cloneable {

    // Medžio šaknies mazgas
    protected BstNode<E> root = null;
    // Medžio dydis
    protected int size = 0;
    // Rodyklė į komparatorių
    protected Comparator<? super E> c;

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparable<E>
     */
    public BstSet() {
        this.c = Comparator.naturalOrder();
    }

    /**
     * Sukuriamas aibės objektas DP-medžio raktams naudojant Comparator<E>
     *
     * @param c Komparatorius
     */
    public BstSet(Comparator<? super E> c) {
        this.c = c;
    }

    /**
     * Patikrinama ar aibė tuščia.
     *
     * @return Grąžinama true, jei aibė tuščia.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return Grąžinamas aibėje esančių elementų kiekis.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Išvaloma aibė.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Patikrinama ar aibėje egzistuoja elementas.
     *
     * @param element - Aibės elementas.
     * @return Grąžinama true, jei aibėje egzistuoja elementas.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }

        return get(element) != null;
    }

    /**
     * Patikrinama ar visi abės set elementai egzistuoja aibėje
     *
     * @param set aibė
     * @return
     */
    @Override
    public boolean containsAll(Set<E> set) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti containsAll(Set<E> set)");
//        for (E s : set){
////            if (!contains(s))
////                return false;
////        }
////        return true;
//        if (set == null) {
//            throw new UnsupportedOperationException("Duotasis medis yra tuscias");
//        }
//        if (root == null) {
//            throw new UnsupportedOperationException("Medis yra tuscias");
//        }
//        boolean containsAll = true;
//        for (E element : set) {
//            if (!this.contains(element)) {
//                containsAll = false;
//            }
//        }
//        return containsAll;
        for (E element : set){
            if(!contains(element)) return false;
        }
        return true;
    }

    /**
     * Aibė papildoma nauju elementu.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }
        root = addRecursive(element, root);
        size++;
    }

    /**
     * Abės set elementai pridedami į esamą aibę, jeigu abi aibės turi tą patį elementą, jis nėra dedamas.
     *
     * @param set pridedamoji aibė
     */
    @Override
    public void addAll(Set<E> set) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti addAll(Set<E> set)");
        for (E element : set) {
            root = addRecursive(element, root);
            size++;
        }
    }

    private BstNode<E> addRecursive(E element, BstNode<E> node) {
        if (node == null) {

            return new BstNode<>(element);
        }

        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.left = addRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = addRecursive(element, node.right);
        }

        return node;
    }

    /**
     * Pašalinamas elementas iš aibės.
     *
     * @param element - Aibės elementas.
     */
    @Override
    public void remove(E element) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti remove(E element)");
        if (element == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }
        removeRecursive(element, root);
        size--;
    }

    /**
     * Aibėje lieka tik tie elementai, kurie yra aibėje set.
     *
     * @param set aibė
     */
    @Override
    public void retainAll(Set<E> set) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti retainAll(Set<E> set)");
//        Iterator iterator = iterator();
//        E current = root.element;
//        while (current != null) {
//            if (!set.contains(current)) {
//                remove(current);
//            }
//            current = (E) iterator.next();
//        }
        BstSet<E> bstSet = (BstSet<E>) set;
        Object[] elements = toArray();
        for (Object element : elements) {
            if (bstSet.get((E) element) == null) {
                remove((E) element);
            }
        }
    }

    private BstNode<E> removeRecursive(E element, BstNode<E> node) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti removeRecursive(E element, BstNode<E> n)");
//        if (node == null) {
//            return node;
//        }
//        int cmp = c.compare(element, node.element);
//
//        if (cmp < 0) {
//            node.left = removeRecursive(element, node.left);
//        } else if (cmp > 0) {
//            node.right = removeRecursive(element, node.right);
//        } else if (node.left != null && node.right != null) {
//            BstNode<E> nodeMax = getMax(node.left);
//            node.element = nodeMax.element;
//            node.left = removeMax(node.left);
//            size--;
//        } else {
//            node = (node.left != null) ? node.left : node.right;
//            size--;
//        }
//
//        return node;
        if (node == null) return node;
        if (element == root.element) {
            root = (node.left != null) ? node.left : node.right;
            return node;
        }
        int cmp = c.compare(element, node.element);
        if (cmp < 0) {
            node.left = removeRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = removeRecursive(element, node.right);
        } else if (node.left != null && node.right != null) {
            BstNode<E> nodeMax = getMax(node.left);

            node.element = nodeMax.element;
            node.left = removeMax(node.left);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;

    }

    private E get(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }
        BstNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.element;
            }
        }

        return null;
    }

    BstNode<E> get(BstNode<E> node) {
        BstNode<E> parent = null;
        while (node != null) {
            parent = node;
            node = node.right;
        }
        return parent;
    }

    /**
     * Grąžina maksimalaus rakto elementą paiešką pradedant mazgu node
     *
     * @param node
     * @return
     */
    BstNode<E> getMax(BstNode<E> node) {
        return get(node);
    }

    /**
     * Pašalina maksimalaus rakto elementą paiešką pradedant mazgu node
     *
     * @param node
     * @return
     */
    BstNode<E> removeMax(BstNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        } else {
            return node.left;
        }
    }

    /**
     * Grąžinamas aibės elementų masyvas.
     *
     * @return Grąžinamas aibės elementų masyvas.
     */
    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[size];
        for (Object o : this) {
            array[i++] = o;
        }
        return array;
    }

    /**
     * Aibės elementų išvedimas į String eilutę Inorder (Vidine) tvarka. Aibės
     * elementai išvedami surikiuoti didėjimo tvarka pagal raktą.
     *
     * @return elementų eilutė
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : this) {
            sb.append(element.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Medžio vaizdavimas simboliais, žiūr.: unicode.org/charts/PDF/U2500.pdf
     * Tai 4 galimi terminaliniai simboliai medžio šakos gale
     */
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge = "\u25CF";
    private static final String vertical = "\u2502  ";
    private String horizontal;

    /* Papildomas metodas, išvedantis aibės elementus į vieną String eilutę.
     * String eilutė formuojama atliekant elementų postūmį nuo krašto,
     * priklausomai nuo elemento lygio medyje. Galima panaudoti spausdinimui į
     * ekraną ar failą tyrinėjant medžio algoritmų veikimą.
     *
     * @author E. Karčiauskas
     */
    @Override
    public String toVisualizedString(String dataCodeDelimiter) {
        horizontal = term[0] + term[0];
        return root == null ? ">" + horizontal : toTreeDraw(root, ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<E> node, String edge, String indent, String dataCodeDelimiter) {
        if (node == null) {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter));
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        sb.append(indent).append(edge).append(horizontal).append(term[t]).append(endEdge).append(split(node.element.toString(), dataCodeDelimiter)).append(System.lineSeparator());
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        sb.append(toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter));
        return sb.toString();
    }

    private String split(String s, String dataCodeDelimiter) {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    /**
     * Sukuria ir grąžina aibės kopiją.
     *
     * @return Aibės kopija.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        BstSet<E> cl = (BstSet<E>) super.clone();
        if (root == null) {
            return cl;
        }
        cl.root = cloneRecursive(root);
        cl.size = this.size;
        return cl;
    }

    private BstNode<E> cloneRecursive(BstNode<E> node) {
        if (node == null) {
            return null;
        }

        BstNode<E> clone = new BstNode<>(node.element);
        clone.left = cloneRecursive(node.left);
        clone.right = cloneRecursive(node.right);
        return clone;
    }

    /**
     * Grąžinamas aibės poaibis iki elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis iki elemento.
     */
    @Override
    public Set<E> headSet(E element) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti headSet()");
        if (element == null || root == null) {
            return null;
        }
        BstSet<E> subset = new BstSet<E>();
        BstNode<E> currentBranch = root;
        BstNode<E> elementBranch = null;
        while (currentBranch != null) {
            int cmp = c.compare(element, currentBranch.element);
            if (cmp < 0) {
                currentBranch = currentBranch.left;
            } else if (cmp > 0) {
                currentBranch = currentBranch.right;
            } else {
                elementBranch = currentBranch;
                currentBranch = null;
            }
        }
        subset.root = cloneRecursive(elementBranch.left);
        return subset;
    }

    @Override
    public Set<E> subSet(E element1, E element2) {
        return subSet(element1, element2, true, false);
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     *
     * @param element1 - pradinis aibės poaibio elementas.
     * @param element2 - galinis aibės poaibio elementas.
     * @return Grąžinamas aibės poaibis nuo elemento element1 iki element2.
     */
//    @Override
    public Set<E> subSet(E element1, E element2, boolean from, boolean to) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti subSet()");
        if (element1 == null || element2 == null || root == null) {
            return null;
        }
        Set<E> subset = new BstSet<E>();
        BstNode<E> currentBranch = root;
        BstNode<E> element1Branch = null;
        while (currentBranch != null) {
            int cmp = c.compare(element1, currentBranch.element);
            if (cmp < 0) {
                currentBranch = currentBranch.left;
            } else if (cmp > 0) {
                currentBranch = currentBranch.right;
            } else {
                element1Branch = currentBranch;
                currentBranch = null;
            }
        }
        if (element1Branch == null) {
            throw new UnsupportedOperationException("1-asis elementas nerastas!");
        }
        boolean flagFound = false;
        while (element1Branch != null) {
            int cmp = c.compare(element2, element1Branch.element);
            if (cmp < 0) {
                if (from) subset.add(element1Branch.element);
                else from = true;
                element1Branch = element1Branch.left;
            } else if (cmp > 0) {
                if (from) subset.add(element1Branch.element);
                else from = true;
                element1Branch = element1Branch.right;
            } else if (cmp == 0) {
                if (to) subset.add(element1Branch.element);
                flagFound = true;
                element1Branch = null;
            }
        }
        if (flagFound) return subset;
        else throw new UnsupportedOperationException("Nepavyko sudaryti poaibio tarp 1 ir 2 elementu!");
    }

    /**
     * Grąžinamas aibės poaibis nuo elemento.
     *
     * @param element - Aibės elementas.
     * @return Grąžinamas aibės poaibis nuo elemento.
     */
    @Override
    public Set<E> tailSet(E element) {
//        throw new UnsupportedOperationException("Studentams reikia realizuoti tailSet()");
        if (element == null || root == null) {
            return null;
        }
        BstSet<E> subset = new BstSet<E>();
        BstNode<E> currentBranch = root;
        BstNode<E> elementBranch = null;
        while (currentBranch != null) {
            int cmp = c.compare(element, currentBranch.element);
            if (cmp < 0) {
                currentBranch = currentBranch.left;
            } else if (cmp > 0) {
                currentBranch = currentBranch.right;
            } else {
                elementBranch = currentBranch;
                currentBranch = null;
            }
        }
        subset.root = cloneRecursive(elementBranch.right);
        return subset;
    }

    /**
     * Grąžinamas tiesioginis iteratorius.
     *
     * @return Grąžinamas tiesioginis iteratorius.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorBst(true);
    }

    /**
     * Grąžinamas atvirkštinis iteratorius.
     *
     * @return Grąžinamas atvirkštinis iteratorius.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new IteratorBst(false);
    }

    /**
     * Vidinė objektų kolekcijos iteratoriaus klasė. Iteratoriai: didėjantis ir
     * mažėjantis. Kolekcija iteruojama kiekvieną elementą aplankant vieną kartą
     * vidine (angl. inorder) tvarka. Visi aplankyti elementai saugomi steke.
     * Stekas panaudotas iš java.util paketo, bet galima susikurti nuosavą.
     */
    private class IteratorBst implements Iterator<E> {

        private final Stack<BstNode<E>> stack = new Stack<>();
        // Nurodo iteravimo kolekcija kryptį, true - didėjimo tvarka, false - mažėjimo
        private final boolean ascending;
        // Reikalinga remove() metodui.
        private BstNode<E> lastInStack;
        private BstNode<E> last;

        IteratorBst(boolean ascendingOrder) {
            this.ascending = ascendingOrder;
            this.toStack(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public E next() {// Jei stekas tuščias
            if (stack.empty()) {
                lastInStack = root;
                last = null;
                return null;
            } else {
                // Grąžinamas paskutinis į steką patalpintas elementas
                BstNode<E> n = stack.pop();
                // Atsimenamas paskutinis grąžintas elementas, o taip pat paskutinis steke esantis elementas.
                // Reikia remove() metodui
                lastInStack = stack.isEmpty() ? root : stack.peek();
                last = n;
                BstNode<E> node = (ascending) ? n.right : n.left;
                // Dešiniajame n pomedyje ieškoma minimalaus elemento,
                // o visi paieškos kelyje esantys elementai talpinami į steką
                toStack(node);
                return n.element;
            }
        }

        @Override
        public void remove() {
//            throw new UnsupportedOperationException("Studentams reikia realizuoti remove()");
            removeRecursive(stack.peek().element, root);
        }

        private void toStack(BstNode<E> n) {
            while (n != null) {
                stack.push(n);
                n = (ascending) ? n.left : n.right;
            }
        }
    }

    /**
     * Vidinė kolekcijos mazgo klasė
     *
     * @param <N> mazgo elemento duomenų tipas
     */
    protected static class BstNode<N> {

        // Elementas
        protected N element;
        // Rodyklė į kairįjį pomedį
        protected BstNode<N> left;
        // Rodyklė į dešinįjį pomedį
        protected BstNode<N> right;

        protected BstNode() {
        }

        protected BstNode(N element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
}
