import java.util.List;
import java.util.ArrayList;


public class ListDrags {

	List<Drag> drags=new ArrayList<Drag>();
	
	public void addDrag(Drag drag){
		drags.add(drag);
	}
	public List<Drag> getDrags(){
		return drags;
	}
	
}
