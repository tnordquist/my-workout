package edu.cnm.deepdive.myworkout.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.myworkout.model.entity.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserDao {

  @Insert
  Single<Long> insert(User user);

  @Insert
  Single<List<Long>> insert(User... users);

  @Insert
  Single<List<Long>> insert(Collection<User> users);

  @Update
  Single<Integer> update(User user);

  @Update
  Single<Integer> update(User... users);

  @Update
  Single<Integer> update(Collection<User> users);

  @Delete
  Single<Integer> delete(User user);

  @Delete
  Single<Integer> delete(User... users);

  @Delete
  Single<Integer> delete(Collection<User> users);


  @Query("SELECT * FROM User WHERE user_id = :id")
  LiveData<User> selectById(long id);

  @Query("SELECT * FROM User WHERE oauth_key = :oauthKey")
  Maybe<User> selectByOauthKey(String oauthKey);

  @Query("SELECT * FROM User ORDER BY user_id")
  LiveData<List<User>> selectAll();
}
