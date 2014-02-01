public class QuadTree {
	final static int QT_NODE_CAPACITY = 4 ;
	List<Point> points ;

	final int x_center ;
	final int y_center ;

	final int x_right ;
	final int x_left ;
	final int y_bottom ;
	final int y_top ;

	QuadTree parent ;
	
	QuadTree TopLeft ;
	QuadTree TopRight ;
	QuadTree BottomLeft ;
	QuadTree BottomRight ;
	
	QuadTree(int x_left, int x_right, int y_top, int y_bottom) {
		this.x_center = (x_right-x_left)/2 + x_left ;
		this.y_center = (y_top-y_bottom)/2 + y_bottom ;
		this.x_right = x_right ;
		this.x_left = x_left ;
		this.y_bottom = y_bottom ;
		this.y_top = y_top ;
		this.points = new ArrayList<Point>(4) ;
	}
	
	QuadTree(int x_left, int x_right, int y_top, int y_bottom, Collection<Point> initialPoints) {
		this(x_left, x_right, y_top, y_bottom) ;
		points.addAll(initialPoints) ;
	}
	
	public boolean inRange(Point p) {
		return p.x >= x_left && p.x <= x_right
				&& p.y <= y_top && p.y >= y_bottom ;		
	}

	public boolean insert(Point p) {
		if(!inRange(p)) {
			return false ;
		}
		
		if(points!=null) {
			if(points.size()<=QT_NODE_CAPACITY) {
				points.add(p) ;
				p.container = this ;
			}
			else { //capacity exceded, divide and insert in the subtrees
				subdivide();
				insert(p) ;
			}
		}
		else {
			//insert in the correct subtree
			if(!TopLeft.insert(p))
				if(!TopRight.insert(p)) 
					if(!BottomLeft.insert(p)) 
						if(!BottomRight.insert(p)) 
							throw new RuntimeException("UPS");
		}
		
		return true ;
	}
	
	// create four children which fully divide this quad into four quads of equal area
	public void subdivide() {
		TopLeft 	= new QuadTree(x_left, 		x_center, y_top, 	  y_center);
		TopRight 	= new QuadTree(x_center,    x_right,  y_top, 	  y_center);
		BottomLeft = new QuadTree( x_left, 		x_center, y_center,   y_bottom);
		BottomRight = new QuadTree(x_center,    x_right,  y_center,   y_bottom);
		
		List<Point> old_points = points ;			
		points = null ;
		
		//add to subtrees
		for(Point p : old_points) {
			insert(p) ;
		}
	} 
	
	public Collection<Point> containedPoints() {
		if(points!=null) 
			return points ;
		else {
			Collection<Point> list = new ArrayList<Point>() ;
			list.addAll(TopLeft.containedPoints()) ;
			list.addAll(TopRight.containedPoints()) ;
			list.addAll(BottomLeft.containedPoints()) ;
			list.addAll(BottomRight.containedPoints()) ;
			return list ;
		}
	}
}

public class Point {
	final double x ;
	final double y ;
	QuadTree container ;
	Point(double x_, double y_) {
		this.x = x_ ;
		this.y = y_ ;
	}

	public double distanceTo_SQRT(Point p) {
		return Math.sqrt((this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y));
	}
	
	public double distanceTo(Point p) {
		return (this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y);
	}
	

	public Collection<Point> adjacentPoints() {
		Collection<Point> result ;
		QuadTree current = this.container ;
		
		//check two levels above current tree
		if(current.parent==null) {
			result = current.containedPoints() ;
		}
		else if(current.parent.parent==null) {
			result = current.parent.containedPoints() ;
		}
		else {
			result = current.parent.parent.containedPoints() ;
		}
		
		result.remove(this) ; //remove itself from result
		return result ;
	}
}
