static int queue_hash_cb(const void *obj, const int flags, struct ast_t *t)
{
	const struct call_queue *q = obj;
	return ast_str_case_hash(q->name);
}
