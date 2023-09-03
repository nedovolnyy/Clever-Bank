/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CleverBank.BusinessLogic.Services;

import CleverBank.DataAccess.Repositories.Interfaces.IRepository;
import Common.Entities.User;

/**
 *
 * @author AKrot
 */
public class UserService extends BaseService<User>{
    private final IRepository<User> _userRepository;
    
    public UserService(IRepository<User> userRepository){
        super(userRepository);
        _userRepository = userRepository;
    }
}
