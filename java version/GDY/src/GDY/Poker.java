package GDY;

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
	public Poker(String sp) {
		int delimitersp = sp.indexOf("-");
		this.p_color = sp.substring(0, delimitersp);
		this.p_point = sp.substring(delimitersp + 1, sp.length());
		this.pic_addr = "/images/" + this.p_color + "-" + this.p_point + ".png";
	}
	public Poker(Poker pa)
	{
		this.p_color = pa.p_color;
		this.p_point = pa.p_point;
		this.pic_addr = "/images/" + this.p_color + "-" + this.p_point + ".png";
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
			return (p_point.equals(other.p_point) && p_point.equals(other.p_color));
	}
	public int compareTo(Poker other)
	{
		int diff = Integer.compare(GameInfo.PRank.get(this.p_point), GameInfo.PRank.get(other.p_point));
		return diff != 0 ? diff : Integer.compare(GameInfo.PRank.get(this.p_color), GameInfo.PRank.get(other.p_color));
	}
	public String toString() {
		return (p_color + "-" + p_point);
	}
}

