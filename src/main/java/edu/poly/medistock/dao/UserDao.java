package edu.poly.medistock.dao;
import edu.poly.medistock.entity.User;

/**
 *
 * @author ADMINZ
 */
public class UserDao {
    
    //phương thức kiểm tra mật khẩu
    public boolean checkUser(User user){
        if(user == null) return false;
        
        if(user.getUserName().equals("a") 
                && user.getPassword().equals("a")){
            return true;
        }
        return false;
    }
}
