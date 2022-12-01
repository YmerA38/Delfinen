package Program;

public class RateMember {
    private Member member;
    private int rating;

    public RateMember(Member member, int rating) {
        this.member = member;
        this.rating = rating;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    public void addToRating(int rating) {
        this.rating += rating;
    }

    public Member getMember() {
        return member;
    }

    public int getRating() {
        return rating;
    }
}
