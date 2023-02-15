package com.mollick.photoapp.domain.use_cases.get_users

import com.mollick.photoapp.common.Resource
import com.mollick.photoapp.data.remote.dto.toUser
import com.mollick.photoapp.domain.model.User
import com.mollick.photoapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/** UsersUseCase to fetch and convert DTO object to model class with current states of API call. */
class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<User>>> = flow {
        android.util.Log.d("BugInfo", "Called GetUsersUseCase invoke. ")
        try {
            emit(Resource.Loading())
            val users = repository.getAllUsers().map { it.toUser() }
            android.util.Log.d("BugInfo", "users size: ${users.size}")
            emit(Resource.Success(users))
        } catch (httpExc: HttpException) {
            emit(Resource.Error(message = httpExc.localizedMessage ?: "An unexpected error."))
        } catch (ioExc: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
        }
    }
}
