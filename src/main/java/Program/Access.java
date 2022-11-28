package Program;

public class Access {
    private Users userType;
    private Member member;

    public void setUserType(Users userType) {
        this.userType = userType;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Users getUserType() {
        return userType;
    }

    public Member getMember() {
        return member;
    }
}
