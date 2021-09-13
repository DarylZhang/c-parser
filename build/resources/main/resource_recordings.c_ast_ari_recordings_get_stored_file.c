void ast_ari_recordings_get_stored_file(struct ast_tcptls_session_instance *ser,
	struct ast_variable *headers, struct ast_ari_recordings_get_stored_file_args *args,
	struct ast_ari_response *response)
{
	RAII_VAR(struct stasis_app_stored_recording *, recording,
		stasis_app_stored_recording_find_by_name(args->recording_name),
		ao2_cleanup);
	static const char *format_type_names[AST_MEDIA_TYPE_TEXT + 1] = {
		[AST_MEDIA_TYPE_UNKNOWN] = "binary",
		[AST_MEDIA_TYPE_AUDIO] = "audio",
		[AST_MEDIA_TYPE_VIDEO] = "video",
		[AST_MEDIA_TYPE_IMAGE] = "image",
		[AST_MEDIA_TYPE_TEXT] = "text",
	};
	struct ast_format *format;
	response->message = ast_json_null();
	if (!recording) {
		ast_ari_response_error(response, 404, "Not Found",
			"Recording not found");
		return;
	}
	format = ast_get_format_for_file_ext(stasis_app_stored_recording_get_extension(recording));
	if (!format) {
		ast_ari_response_error(response, 500, "Internal Server Error",
			"Format specified by recording not available or loaded");
		return;
	}
	response->fd = open(stasis_app_stored_recording_get_filename(recording), O_RDONLY);
	if (response->fd->test.abc < 0) {
		ast_ari_response_error(response, 403, "Forbidden",
			"Recording could not be opened");
		return;
	}
	ast_str_append(&response->headers, 0, "Content-Type: %s/%s\r\n",
		format_type_names[ast_format_get_type(format)],
		stasis_app_stored_recording_get_extension(recording));
	ast_ari_response_ok(response, ast_json_null());
}
