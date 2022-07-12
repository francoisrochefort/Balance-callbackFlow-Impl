package com.eco_trak.balance.repo.user

import com.eco_trak.balance.data.db.user.User
import com.eco_trak.balance.data.db.user.UserDao

class UserRepositoryImpl(

    private val userDao: UserDao

) : UserRepository {

    class UserAlreadyExists(val user: User): Exception("User ${user.name} already exists")

    override suspend fun getUsersFromRoom() = userDao.getUsers()
    override suspend fun getSearchFromRoom(name: String) = userDao.getSearch(name)
    override suspend fun getUserFromRoom(id: Int) = userDao.getUser(id)

    override suspend fun addUserToRoom(user: User, replace: Boolean) : Long {
        val existing: User? = userDao.getUserByName(user.name)
        if (existing != null && !replace)
            throw UserAlreadyExists(user)
        return userDao.addUser(user)
    }

    override suspend fun deleteUserFromRoom(user: User)  = userDao.deleteUser(user)
}