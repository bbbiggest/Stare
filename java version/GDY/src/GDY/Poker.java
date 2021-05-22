package GDY;
import java.util.ArrayList;
import java.util.Scanner;

public class Poker implements Comparable<Poker>
{
	private String p_color;
	private String p_point;
	private String pic_addr;
	public Poker() {}
	public Poker(String p_color, String p_point)
	{
		this.p_color = p_color;
        this.p_point = p_point;
        this.pic_addr = "/images/" + p_color + "-" + p_point + ".png";
	}
	public Poker(Poker pa)
	{
		this.p_color = pa.p_color;
		this.p_point = pa.p_point;
	}
	public String getColor()
	{
		return this.p_color;
	}
	public String getPoint()
	{
		return this.p_point;
	}
	public String getPic_addr() { return this.pic_addr; }
	public boolean equals(Object otherObject)
	{
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Poker other = (Poker)otherObject;
			return p_point.equals(other.p_point) && p_point.equals(other.p_color);
	}
	public int compareTo(Poker other)
	{
		int diff = Integer.compare(gandengyan.PRank.get(this.p_point), gandengyan.PRank.get(other.p_point));
		return diff != 0 ? diff : Integer.compare(gandengyan.PRank.get(this.p_color), gandengyan.PRank.get(other.p_color));
	}
}

