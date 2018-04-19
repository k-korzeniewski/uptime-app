package me.wiz3ard.uptimeapp.Server;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<ServerModel, ObjectId> {

}
