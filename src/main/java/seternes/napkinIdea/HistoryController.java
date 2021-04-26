package seternes.napkinIdea;

import java.util.ArrayList;

// historyController klassen behandler alt av Undo/Redo og tegningen av alle tegneoppgavene som har blitt gjort.
public class HistoryController {
    private ArrayList<Layer> totalHistory = new ArrayList<Layer>();
    private ArrayList<Layer> undoHistory = new ArrayList<Layer>();
    private ArrayList<Layer> redoHistory = new ArrayList<Layer>();
    private final int maxSize = 16;

    public Layer popUndoHistory() {
        if(this.undoHistory.isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Layer data = this.undoHistory.get(this.undoHistory.size() - 1);
        this.undoHistory.remove(this.undoHistory.size() - 1);
        return data;
    }

    public void pushUndoHistory(Layer l) {
        if(!(l instanceof Layer)) throw new IllegalArgumentException();
        if(this.undoHistory.size() >= this.maxSize) {
            this.totalHistory.add(this.undoHistory.get(0));
            this.undoHistory.remove(0);
        }
        this.undoHistory.add(l);
    }

    public Layer getIndexUndoHistory(int i) {
        if(this.undoHistory.isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if(i < 0 || (i + 1) > this.undoHistory.size()) throw new ArrayIndexOutOfBoundsException();
        return this.undoHistory.get(i);
    }

    public Layer popRedoHistory() {
        if(this.redoHistory.isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Layer data = this.redoHistory.get(this.redoHistory.size() - 1);
        this.redoHistory.remove(this.redoHistory.size() - 1);
        return data;
    }

    public void pushRedoHistory(Layer l) {
        if(!(l instanceof Layer)) throw new IllegalArgumentException();
        if(this.redoHistory.size() >= this.maxSize) this.redoHistory.remove(0);
        this.redoHistory.add(l);
    }

    public Layer getIndexRedoHistory(int i) {
        if(this.redoHistory.isEmpty()) throw new ArrayIndexOutOfBoundsException();
        if(i < 0 || i > this.redoHistory.size()) throw new ArrayIndexOutOfBoundsException();
        return this.redoHistory.get(i);
    }

    public int getUndoHistorySize() {
        return this.undoHistory.size();
    }

    public int getRedoHistorySize() {
        return this.redoHistory.size();
    }

    public ArrayList<Layer> getTotalHistory() {
        return this.totalHistory;
    }

    public ArrayList<Layer> getUndoHistory() {
        return this.undoHistory;
    }

    public ArrayList<Layer> getRedoHistory() {
        return this.redoHistory;
    }
}
