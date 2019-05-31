extern crate hustle_api;
extern crate jni;

use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::{jlong, jboolean, jint};
use hustle_api::connection::HustleConnection;
use hustle_api::statement::HustleStatement;
use hustle_api::result::HustleResult;

#[no_mangle]
pub extern "system" fn Java_HustleJNI_hustleConnectionNew(
    _env: JNIEnv,
    _class: JClass
) -> jlong {
    Box::into_raw(Box::new(HustleConnection::new())) as jlong
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleConnectionPrepare(
    env: JNIEnv,
    _class: JClass,
    connection_ptr: jlong,
    sql: JString
) -> jlong {
    let connection = &*(connection_ptr as *mut HustleConnection);
    let sql_string: String = env.get_string(sql)
        .expect("Could not get SQL from Java")
        .into();
    Box::into_raw(Box::new(connection.prepare(&sql_string))) as jlong
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustlePreparedStatementExecuteQuery(
    _env: JNIEnv,
    _class: JClass,
    prepared_statement_ptr: jlong
) -> jlong {
    let statement = &mut *(prepared_statement_ptr as *mut HustleStatement);
    match statement.execute() {
        Ok(result) => result
            .map(|r| Box::into_raw(Box::new(r)) as jlong)
            .unwrap_or(0 as jlong),
        Err(_error) => 0 as jlong
    }
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleResultNext(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong
) -> jboolean {
    let result = &mut *(result_ptr as *mut HustleResult);
    result.step() as jboolean
}

#[no_mangle]
pub unsafe extern "system" fn Java_HustleJNI_hustleResultGetLong(
    _env: JNIEnv,
    _class: JClass,
    result_ptr: jlong,
    column_index: jint
) -> jlong {
    let result = &mut *(result_ptr as *mut HustleResult);
    result.get_i64(column_index as usize).unwrap() as jlong
}
